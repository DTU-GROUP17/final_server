package weighting.answers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoadAnswer extends Answer {

	public enum LoadStatus {
		I,
		S,
		PLUS,
		MINUS;

		public static LoadStatus from(String string) {
			switch (string) {
				case "I": return LoadStatus.I;
				case "S": return LoadStatus.S;
				case "+": return LoadStatus.PLUS;
				case "-": return LoadStatus.MINUS;
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

	private static final Pattern pattern = Pattern.compile(" ([^\\s])(\\s+(-?\\d+\\.?\\d*)\\s+((.*)))?");

	@Getter@Setter(AccessLevel.PRIVATE)
	private LoadStatus status;
	@Getter@Setter(AccessLevel.PRIVATE)
	private double load;
	@Getter@Setter(AccessLevel.PRIVATE)
	private String unit;

	public LoadAnswer(String values) throws ParseAnswerException {
		Matcher matcher;
		try {
			matcher = pattern.matcher(values);
			matcher.matches();
			this.setStatus(LoadStatus.from(matcher.group(1)));
			if (
				matcher.group(3) != null
				&& matcher.group(4)!=null
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
