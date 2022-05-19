package protocolo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.HashMap;
import java.util.Map;

// Thread para ouvir a comunica��o multicast
public class Transmissao extends Thread {
	protected MulticastSocket receptorMulticast;
	protected InetAddress grupo;
	protected byte[] buffer = new byte[256];
	protected String assinatura;
	protected Map<String, Integer> processos;
	protected int testeFalha = 0;
	protected Processo processo;
	Verificador verificador = new Verificador();

	public Transmissao(String assinatura, Processo processo) throws IOException {
		this.assinatura = assinatura;
		this.receptorMulticast = new MulticastSocket(4446);
		this.grupo = InetAddress.getByName("230.0.0.0");
		this.receptorMulticast.joinGroup(this.grupo);
		this.processo = processo;
		this.processos = new HashMap<>();
	}

	public void run() {
		try {
			while (true) {
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				this.receptorMulticast.receive(packet);
				String recebido = new String(packet.getData(), 0, packet.getLength());
				String divisao[] = recebido.split("!");
				System.out.println("Mensagem recebida de " + divisao[1]);
				if ("apresentacao".equalsIgnoreCase(divisao[0])) {
					System.out.println("Se apresente " + this.assinatura);
					// Recebeu mensagem para se apresentar
					// J� adiciona quem enviou na lista
					this.processos.put(divisao[1], 0);
					processo.callback(divisao[0] + ",");
				} else if ("meuNome".equalsIgnoreCase(divisao[0])) {
					System.out.println("Processo " + divisao[1] + " se apresentou");
					this.processos.put(divisao[1], 0);
				} else if ("exclude".equalsIgnoreCase(divisao[0])) {
					if (divisao[1].equalsIgnoreCase(this.assinatura)) {
						System.out.println("Processo finalizado por mal funcionamento");
						break;
					} else {
						System.out.println("Processo " + divisao[1] + " foi exclu�do do grupo");
					}
				} else if ("sem".equalsIgnoreCase(divisao[0])) {
					processo.callback("respSem,");
				} else if ("com".equalsIgnoreCase(divisao[0])) {
					processo.callback("respCom,");
				} else if ("queda".equalsIgnoreCase(divisao[0])) {
					verificador.alterarEnvio(true);
					verificador.run();
					processo.callback("respSem,queda");
					verificador.alterarEnvio(false);
				} else {
					// Respostas
					String teste[] = divisao[0].split("@");
					if ("respSem".equalsIgnoreCase(teste[0])) {
						if (teste.length > 2) {
							testeFalha++;
							// Armazena quem ainda est� conectado no grupo
							int votos = processos.get(divisao[1]) + 1;
							processos.put(divisao[1], votos);
							if (testeFalha >= processos.size() - 1) {
								for (Object key : processos.keySet()) {
									int send = processos.get(key.toString());
									if (send == 0) {
										System.out.println("Processo " + key + " n�o respondeu, portanto ele ser� removido do grupo");
										processo.callback("terminate," + key);
									}
								}
								for (Object key : processos.keySet()) {
									this.processos.put(key.toString(), 0);
								}
								testeFalha = 0;
							}
						}
						System.out.println("Processo " + divisao[1] + " respondeu " + teste[1]);
					} else if ("respCom".equalsIgnoreCase(teste[0])) {
						if (teste[1].equalsIgnoreCase("0")) {
							System.out.println("Processo " + divisao[1] + " respondeu " + teste[1]);
						} else if (!divisao[1].equalsIgnoreCase(this.assinatura)) {
							System.out.println("Processo " + divisao[1] + " respondeu " + teste[1]);
							System.out.println("Aparentemente o processo est� com problemas, portanto ele ser� removido do grupo");
							processo.callback("voteTerminate," + divisao[1]);
						}
					} else if ("voteTerminate".equalsIgnoreCase(teste[0])) {
						System.out.println("Vota��o para finalizar o processo " + teste[1] + " recebida");
						// Armazena a vota��o
						int votos = processos.get(teste[1]) + 1;
						processos.put(teste[1], votos);
						if (votos >= 2) {
							processo.callback("terminate," + teste[1]);
							processos.put(teste[1], 0);
						}
					}
				}
			}
			processo.callback("endMe,");
			System.out.println("Processo " + this.assinatura + " exclu�do do grupo");
			this.receptorMulticast.leaveGroup(grupo);
			this.receptorMulticast.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}