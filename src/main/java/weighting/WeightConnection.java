package weighting;

import java.io.IOException;
import java.net.Socket;

public class WeightConnection extends Thread {

	private String uri;
	private boolean running;
	private boolean status;
	private WeightController controller;

	public WeightConnection(String uri) {
		this.uri = uri;
	}

	private void connect(){
		try {
			Socket socket = new Socket(this.uri, 8000);
			socket.setSoTimeout(10 * 1000);
			System.out.println("new socket");
			this.controller = new WeightController(socket);
			this.setStatus(true);
		} catch (IOException e) {
			System.out.println("socket failed");
//			this.stopRunning();
		}

	}

	@Override
	public void run() {
		WeightingSession session;
		this.running = true;
		this.connect();
		while (this.isRunning()) {
			try {
				session = new WeightingSession(controller);
				System.out.println("session made");
				session.run();
			} catch (WeightingSessionException e) {
				try {
					sleep(5 * 1000);
				} catch (InterruptedException exception) {
					this.interrupt();
				}
			} catch (DisconnectedException e) {
				this.connect();
			}
		}
	}

	private void setStatus(boolean status) {
		this.status = status;
	}

	public void stopRunning() {
		System.out.println("stop running");
		this.setStatus(false);
		this.running = false;
	}

	public boolean isRunning() {
		return this.running;
	}

	public boolean getStatus() {
		return this.status;
	}
}
