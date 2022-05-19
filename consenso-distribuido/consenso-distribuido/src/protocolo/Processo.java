package protocolo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public abstract class Processo {
	// Entidade que estabelece as regras da comunicação multicast
	protected DatagramSocket datagrama;
	protected InetAddress grupo;
	protected byte[] buffer;
	protected String assinatura;
	
	public void enviarMulticast(String mensagem) {
		try {
			mensagem = mensagem + "!" + this.assinatura;
			buffer = mensagem.getBytes();
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, grupo, 4446);
			datagrama.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void callback(String acao) {
		String opcao[] = acao.split(",");
		if (opcao[0].equalsIgnoreCase("apresentacao")) {
			System.out.println(this.assinatura + " se apresentando");
			enviarMulticast("meuNome");
		} else if (opcao[0].equalsIgnoreCase("respSem")) {
			if (opcao.length > 1 && opcao[1].equalsIgnoreCase("queda")) {
				System.out.println("Enviando resposta 0");
				enviarMulticast("respSem@0@ok");
			} else {
				System.out.println("Enviando resposta 0");
				enviarMulticast("respSem@0");
			}
		} else if (opcao[0].equalsIgnoreCase("respCom")) {
			if (this.assinatura != "P3") {
				System.out.println("Enviando resposta 0");
				enviarMulticast("respCom@0");
			} else {
				System.out.println("Enviando resposta 1");
				enviarMulticast("respCom@1");
			}
		} else if (opcao[0].equalsIgnoreCase("voteTerminate")) {
			System.out.println("Votação terminar o processo " + opcao[1]);
			enviarMulticast("voteTerminate@" + opcao[1]);
		} else if (opcao[0].equalsIgnoreCase("terminate")) {
			System.out.println("Excluir processo por votação " + opcao[1]);
			enviarMulticast("exclude!" + opcao[1]);
		}
	}
}
