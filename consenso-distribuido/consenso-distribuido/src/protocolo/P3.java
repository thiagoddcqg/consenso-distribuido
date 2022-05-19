package protocolo;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

// Processo que apresenta falha bizantina e tamb�m falha por queda ou por omiss�o
public class P3 extends Processo {
	public P3() throws IOException {
		this.assinatura = "P3";
		this.datagrama = new DatagramSocket();
		this.grupo = InetAddress.getByName("230.0.0.0");
		// Se fez o login corretamente inicia o ouvinte do multicast
		Transmissao multicast = new Transmissao(this.assinatura, this);
		Thread t = new Thread(multicast);
		t.start();
		System.out.println("Processo " + this.assinatura + " est� pronto");
	}
}