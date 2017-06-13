package weighting.answers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ToString
public class SerialNumberInqueryAnswer extends Answer {

	public enum Status {
		I,
		A
	}

	private static final Pattern pattern = Pattern.compile(" (\\w)( \"(.*?)\")?");

	@Getter @Setter(AccessLevel.PRIVATE)
	private SerialNumberInqueryAnswer.Status type;
	@Getter@Setter(AccessLevel.PRIVATE)
	private String content;

	public SerialNumberInqueryAnswer(String values) throws ParseAnswerException {
		System.out.println(this);
		Matcher matcher;
		try {
			matcher = pattern.matcher(values);
			matcher.matches();
			this.setType(SerialNumberInqueryAnswer.Status.valueOf(matcher.group(1)));
		} catch (IllegalArgumentException | IllegalStateException e) {
			throw new ParseAnswerException();
		}
		if (matcher.groupCount()>2) {
			this.setContent(matcher.group(2));
		}
		System.out.println(this);
	}
}
