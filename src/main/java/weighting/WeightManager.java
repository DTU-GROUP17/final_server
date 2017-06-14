package weighting;

import lombok.Getter;
import lombok.Setter;
import weighting.answers.*;
import weighting.commands.*;

import java.io.*;
import java.net.Socket;

public class WeightManager {

	private BufferedReader reader;
	private PrintWriter writer;
	@Setter@Getter private int maxTimeoutAttempts = 3;

	public WeightManager(Socket connection) throws IOException {
		this.reader = new BufferedReader(
			new InputStreamReader(connection.getInputStream())
		);
		this.writer = new PrintWriter(
			new OutputStreamWriter(connection.getOutputStream())
		);
	}

	private void send(String message) {
		System.out.println("message = " + message);
		writer.write(message+"\r\n");
		writer.flush();
	}

	private void send(Command command) {
		this.send(command.toString());
	}

	private <T extends Answer> T attemptGetAnswer(Class<T> answerClass) throws IOException {
		while (true) {
			try {
				return Answer.parseSpecific(
					reader.readLine(),
					answerClass
				);
			} catch (ParseAnswerException e) {
			}
		}
	}

	private <T extends Answer> T getAnswer(Class<T> answerClass) {
		int attempts = 0;
		while (attempts<this.getMaxTimeoutAttempts()) {
			attempts += 1;
			try {
				return this.attemptGetAnswer(answerClass);
			} catch (IOException e) {
				try {
					this.send(
						new InquireSerialNumber()
					);
					this.attemptGetAnswer(SerialNumberInqueryAnswer.class);
				} catch (IOException verify) {
					throw new DisconnectedException();
				}
			}
		}
		throw new DisconnectedException();
	}

	public String prompt(String prompt, String defaultDisplay) {
		this.send(
			new Prompt()
				.setAcceptType(Prompt.AcceptType._4)
					.setPrompt(prompt)
						.setDefaultDisplay(defaultDisplay)
		);
		if (this.getAnswer(PromptAnswer.class).getType() != PromptAnswer.Status.B) {
			throw new WeightingSessionException();
		}
		PromptAnswer answer = this.getAnswer(PromptAnswer.class);
		if (answer.getType() != PromptAnswer.Status.A || answer.getContent() == null) {
			throw new WeightingSessionException();
		}
		return answer.getContent();
	}

	public void confirmedMessage(String message) {
		this.send(
			new DisplayPopup()
				.setWindowType(DisplayPopup.WindowType._2)
					.setDisplay(message)
		);
		if (this.getAnswer(PopupAnswer.class).getType() != PopupAnswer.Status.B) {
			throw new WeightingSessionException();
		}
		PopupAnswer answer = this.getAnswer(PopupAnswer.class);
		if (
			answer.getType() != PopupAnswer.Status.A
			|| answer.getContent() == null
			|| answer.getContent() != PopupAnswer.UserResponse.OK) {
			throw new WeightingSessionException();
		}
	}

	public void reset() {
		this.send(
			new Reset()
		);
	}

	public void displayMessage(String message) {
		this.send(
			new DisplayMessage()
				.setDisplay(message)
		);
	}

	public double getStapleLoad() {
		while (true) {
			this.send(
				new RequestLoad()
			);
			LoadAnswer answer = this.getAnswer(LoadAnswer.class);
			switch (answer.getStatus()) {
				case S: return answer.getLoad();
				case MINUS:
					this.confirmedMessage("Weight under minimum load");
					continue;
				case PLUS:
					this.confirmedMessage("Weight over maximum load");
					continue;
				default: throw new WeightingSessionException();
			}
		}
	}

	public void tare(){
		while (true) {
			this.send(
				new Tare()
			);
			TareAnswer answer = this.getAnswer(TareAnswer.class);
			System.out.println("answer = " + answer);
			switch (answer.getStatus()) {
				case S: return;
				case MINUS:
					this.confirmedMessage("Weight under minimum load, tare failed");
					continue;
				case PLUS:
					this.confirmedMessage("Weight over maximum load, tare failed");
					continue;
				default: throw new WeightingSessionException();
			}
		}
	}

}
