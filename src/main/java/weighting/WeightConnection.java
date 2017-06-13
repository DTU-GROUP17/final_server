package weighting;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class WeightConnection extends Thread {

	private String uri;
	private boolean running;
	private boolean status;

	public WeightConnection(String uri) {
		this.uri = uri;
	}

	@Override
	public void run() {
		WeightController controller;
		WeightingSesssion sesssion;
		while (this.isRunning()) {
			try {
				Socket socket = new Socket(this.uri, 8000);
				socket.setSoTimeout(10*60*1000);
				controller = new WeightController(socket);
				this.setStatus(true);
			} catch (IOException e) {
				this.stopRunning();
				continue;
			}
			try {
				sesssion = new WeightingSesssion(controller);
				sesssion.run();
			} catch (SocketTimeoutException e) {
				this.setStatus(true);
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
