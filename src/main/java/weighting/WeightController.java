package weighting;

import lombok.Getter;
import lombok.Setter;
import weighting.answers.Answer;
import weighting.answers.ParseAnswerException;
import weighting.answers.PromptAnswer;
import weighting.answers.SerialNumberInqueryAnswer;
import weighting.commands.*;

import java.io.*;
import java.net.Socket;

public class WeightController {

	private BufferedReader reader;
	private PrintWriter writer;
	@Setter@Getter private int maxTimeoutAttempts = 3;

	public WeightController(Socket connection) throws IOException {
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
					.setPrompt("Operator#")
						.setDefaultDisplay("")
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

	public void reset() {
		this.send(
			new Reset()
		);
	}

	public void sendMessage(String message) {
		this.send(
			new DisplayMessage()
				.setDisplay(message)
		);
	}

	public double getStapleLoad() {
		this.send(
			new RequestLoad()
		);

		return 0.0;
	}

	public void tara(){

	}

}
