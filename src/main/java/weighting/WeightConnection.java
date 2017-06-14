package weighting;

import models.db.Weight;

import java.io.IOException;
import java.net.Socket;

public class WeightConnection extends Thread {

	private String uri;
	private boolean running;
	private boolean status;
	private Weight weight;
	private WeightManager controller;

	public WeightConnection(String uri, Weight weight) {
		this.uri = uri;
		this.weight = weight;
	}

	private void connect(){
		try {
			Socket socket = new Socket(this.uri, 8000);
			socket.setSoTimeout(60 * 1000);
			System.out.println("new socket");
			this.controller = new WeightManager(socket);
			this.setStatus(true);
		} catch (IOException e) {
			System.out.println("socket failed");
		}

	}

	@Override
	public void run() {
		WeightingSession session;
		this.running = true;
		this.connect();
		while (this.isRunning()) {
			try {
				session = new WeightingSession(controller, weight);
				this.controller.reset();
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
