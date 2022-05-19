package protocolo;

import java.util.Timer;
import java.util.TimerTask;

class Verificador extends TimerTask {
	private boolean envio;

	public void alterarEnvio(boolean valor) {
		this.envio = valor;
	}
	
	@Override
	public void run() {
		if (this.envio) {
			// Verifica quem est� ativo a cada 5 segundos
			try {
				Timer timer = new Timer();
				timer.schedule(new Verificador(), 5000);
				Thread.sleep(5000);
				System.out.println("Verifica��o de 5 segundos de que processos ainda est�o conectados");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}