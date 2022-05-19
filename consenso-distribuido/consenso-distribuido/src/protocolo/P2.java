package protocolo;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

// Processo saud�vel necess�rio para que haja um n�mero �mpar de processos no grupo
// A fim de se almejar o consenso por voto majorit�rio caso algum processo esteja corrompido
public class P2 extends Processo {
	public P2() throws IOException {
		this.assinatura = "P2";
		this.datagrama = new DatagramSocket();
		this.grupo = InetAddress.getByName("230.0.0.0");
		// Se fez o login corretamente inicia o ouvinte do multicast
		Transmissao multicast = new Transmissao(this.assinatura, this);
		Thread t = new Thread(multicast);
		t.start();
		System.out.println("Processo " + this.assinatura + " est� pronto");
	}
}