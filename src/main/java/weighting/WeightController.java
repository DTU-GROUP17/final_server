package weighting;

import java.io.*;
import java.net.Socket;

public class WeightController {

	private BufferedReader reader;
	private PrintWriter writer;

	public WeightController() {
	}

	public WeightController(Socket connection) throws IOException {
		this.reader = new BufferedReader(
			new InputStreamReader(connection.getInputStream())
		);
		this.writer = new PrintWriter(
			new OutputStreamWriter(connection.getOutputStream())
		);
	}

	private String getValue(String query) {
		try {
			this.writer.println(query);
			return this.reader.readLine();
		} catch (IOException e) {
			throw new WeightingSessionException();
		}
	}

	public String getAnswer(String query, String defaultDisplay) {
		String answer = this.getValue(
			String.format("RM20 4 \"%s\" \"%s\" \"\"", query, defaultDisplay)
		);
		return "";
	}

	public void sendMessage(String message) {

	}

	public double getLoad() {
		return 0.0;
	}

	public void tara(){

	}

}
