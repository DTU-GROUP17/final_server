package weighting.answers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoadAnswer extends Answer {

	public enum Status {
		I,
		S,
		PLUS,
		MINUS;

		public static Status _valueOf(String string) {
			switch (string) {
				case "I": return Status.I;
				case "S": return Status.S;
				case "+": return Status.PLUS;
				case "-": return Status.MINUS;
				default: throw new IllegalArgumentException();
			}
		}

		@Override
		public String toString() {
			switch (this) {
				case I: return "I";
				case S: return "S";
				case PLUS: return "+";
				case MINUS: return "-";
				default: throw new IllegalArgumentException();
			}
		}
	}

	private static final Pattern pattern = Pattern.compile(" S\\s+(\\d+\\.?\\d*) ((.*))");

	@Getter
	@Setter(AccessLevel.PRIVATE)
	private PromptAnswer.Status type;
	@Getter@Setter(AccessLevel.PRIVATE)
	private String content;

	public LoadAnswer(String values) throws ParseAnswerException {
		Matcher matcher;
		try {
			matcher = pattern.matcher(values);
			matcher.matches();
			this.setType(PromptAnswer.Status.valueOf(matcher.group(1)));
		} catch (IllegalArgumentException | IllegalStateException e) {
			throw new ParseAnswerException();
		}
		if (matcher.groupCount()>2) {
			this.setContent(matcher.group(2));
		}
		System.out.println(this);
	}

}
