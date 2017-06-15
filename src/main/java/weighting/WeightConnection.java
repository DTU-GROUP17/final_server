package weighting;

import models.db.Weight;

import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

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
			String string = this.uri.contains("//") ? this.uri : "//" + this.uri;
			URI uri = new URI(string);
			Socket socket = new Socket(
				uri.getHost() + uri.getPath(),
				uri.getPort() == -1 ? 8000 : uri.getPort()
			);
			socket.setSoTimeout(60 * 1000);
			this.controller = new WeightManager(socket);
			this.setStatus(true);
		} catch (IOException | URISyntaxException e) {
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
				try{
					this.controller.reset();
				} catch (NullPointerException e) {
					throw new DisconnectedException();
				}
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
