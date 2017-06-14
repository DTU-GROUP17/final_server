package weighting.answers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ToString
public class PromptAnswer extends Answer {

	public enum Status {
		B,
		I,
		L,
		A,
		C,
	}

	private static final Pattern pattern = Pattern.compile(" (\\w)( \"((.*?))\")?");

	@Getter@Setter(AccessLevel.PRIVATE)
	private Status type;
	@Getter@Setter(AccessLevel.PRIVATE)
	private String content;

	public PromptAnswer(String values) throws ParseAnswerException {
		Matcher matcher;
		try {
			matcher = pattern.matcher(values);
			matcher.matches();
			this.setType(Status.valueOf(matcher.group(1)));
		} catch (IllegalArgumentException | IllegalStateException e) {
			throw new ParseAnswerException();
		}
		if (matcher.group(3)!=null) {
			this.setContent(matcher.group(3));
		}
	}

}
