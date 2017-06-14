package weighting.answers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PopupAnswer extends Answer {

	public enum Status {
		B,
		I,
		L,
		A
	}

	public enum UserResponse {
		OK,
		CANCELED;

		public static UserResponse fromString(String string) {
			switch (string) {
				case "1": return OK;
				case "2": return CANCELED;
				default: throw new IllegalArgumentException();
			}
		}
	}

	private static final Pattern pattern = Pattern.compile(" (\\w)( ((\\d)))?");

	@Getter@Setter(AccessLevel.PRIVATE)
	private PopupAnswer.Status type;
	@Getter@Setter(AccessLevel.PRIVATE)
	private UserResponse content;

	public PopupAnswer(String values) throws ParseAnswerException {
		Matcher matcher;
		try {
			matcher = pattern.matcher(values);
			matcher.matches();
			this.setType(PopupAnswer.Status.valueOf(matcher.group(1)));
			if (matcher.group(3)!=null) {
				this.setContent(
					UserResponse.fromString(
						matcher.group(3)
					)
				);
			}
		} catch (IllegalArgumentException | IllegalStateException e) {
			throw new ParseAnswerException();
		}

	}

}
