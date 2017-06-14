package weighting.commands;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class DisplayPopup extends Command {

	public static final String head = "RM49";

	public enum WindowType {
		_1, _2, _3, _4;

		@Override
		public String toString() {
			return Character.toString(super.toString().charAt(1));
		}
	}

	@Getter@Setter private String display;
	@Getter@Setter private WindowType windowType;

	@Override
	public String toString() {
		return String.format(
			"%s %s \"%s\"",
			DisplayPopup.head,
			this.windowType.toString(),
			this.getDisplay()
		);
	}
}
