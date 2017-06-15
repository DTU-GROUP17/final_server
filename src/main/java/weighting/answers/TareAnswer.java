package weighting.answers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TareAnswer extends Answer {

	private static final Pattern pattern = Pattern.compile(" ([^\\s])(\\s+(-?\\d+\\.?\\d*)\\s+((.*)))?");

	@Getter@Setter(AccessLevel.PRIVATE)
	private LoadAnswer.LoadStatus status;
	@Getter@Setter(AccessLevel.PRIVATE)
	private double load;
	@Getter@Setter(AccessLevel.PRIVATE)
	private String unit;

	public TareAnswer(String values) throws ParseAnswerException {
		Matcher matcher;
		try {
			matcher = pattern.matcher(values);
			matcher.matches();
			this.setStatus(LoadAnswer.LoadStatus.from(matcher.group(1)));
			if (
				matcher.group(3) != null
				&& matcher.group(4) != null
			) {
				this.setLoad(
					Double.parseDouble(
						matcher.group(3)
					)
				);
				this.setUnit(matcher.group(4));
			}
		} catch (IllegalArgumentException | IllegalStateException e) {
			throw new ParseAnswerException();
		}
	}

}
