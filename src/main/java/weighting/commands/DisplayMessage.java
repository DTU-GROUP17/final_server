package weighting.commands;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class DisplayMessage extends Command {

	public static final String head = "P111";

	@Getter@Setter private String display;

	@Override
	public String toString() {
		return String.format(
			"%s \"%s\"",
			DisplayMessage.head,
			this.getDisplay()
		);
	}

}
