package weighting.commands;

public class Reset extends Command {

	public static final String head = "@";

	@Override
	public String toString() {
		return String.format(
			"%s",
			Reset.head
		);
	}
}
