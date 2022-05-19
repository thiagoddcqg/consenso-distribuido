package protocolo;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

// Processo saud�vel com interface (menu de op��es)
public class P1 extends Processo {
	protected Scanner entrada;
	
	public P1() throws IOException {
		this.assinatura = "P1";
		this.datagrama = new DatagramSocket();
		this.grupo = InetAddress.getByName("230.0.0.0");
		// Se o login foi feito corretamente
		// Inicia o ouvinte do multicast
		Transmissao multicast = new Transmissao(this.assinatura, this);
		Thread t = new Thread(multicast);
		t.start();
		this.entrada = new Scanner(System.in);
		System.out.println("Processo " + this.assinatura + " est� pronto");
		menu();
	}
	
	public void menu() {
		System.out.println("Se todos os processos estiverem rodando digite (sim)");
		String opcao = entrada.nextLine();
		if (opcao.equalsIgnoreCase("sim")) {
			enviarMulticast("apresentacao");
		}
		while (true) {
			System.out.println("--------------------------------------------------------");
			System.out.println("Iniciar comunica��o (sem) falha, (com) falha ou (queda)?");
			opcao = entrada.nextLine();
			if (opcao.equalsIgnoreCase("sem")) {
				System.out.println("Qual � o valor de decis�o?");
				enviarMulticast("sem");
			} else if (opcao.equalsIgnoreCase("com")) {
				System.out.println("Qual � o valor de decis�o?");
				enviarMulticast("com");
			} else if (opcao.equalsIgnoreCase("queda")) {
				System.out.println("Qual � o valor de decis�o?");
				enviarMulticast("queda");
			}
		}
	}
}