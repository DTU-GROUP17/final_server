package weighting.commands;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class Prompt extends Command {

	public static final String head = "RM20";

	public enum AcceptType {
		_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11;

		@Override
		public String toString() {
			return Character.toString(super.toString().charAt(1));
		}
	}

	@Getter@Setter private AcceptType acceptType;
	@Getter@Setter private String prompt;
	@Getter@Setter private String defaultDisplay;

	@Override
	public String toString() {
		return String.format(
			"%s %s \"%s\" \"%s\" \"\"",
			Prompt.head,
			this.getAcceptType().toString(),
			this.getPrompt(),
			this.getDefaultDisplay()
		);
	}
}
