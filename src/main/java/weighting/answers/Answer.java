package weighting.answers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Answer {

	private static final Pattern commandPattern = Pattern.compile("([\\w\\d]+)((.*))");

	public static Answer parseFromString(String string) throws ParseAnswerException {
		System.out.println("parse: " + string);
		Matcher matcher;
		try {
			matcher = commandPattern.matcher(string);
			matcher.matches();
		} catch (IllegalStateException e) {
			throw new ParseAnswerException();
		}
		switch (matcher.group(1)) {
			case "RM20": return new PromptAnswer(matcher.group(2));
			case "I4": return new SerialNumberInqueryAnswer(matcher.group(2));
			default: throw new ParseAnswerException();
		}
	}

	public static <T extends Answer> T parseSpecific(String string, Class<T> answerClass) throws ParseAnswerException {
		Answer answer = Answer.parseFromString(string);
		if (!answer.getClass().isAssignableFrom(answerClass)) {
			throw new ParseAnswerException();
		}
		try {
			return (T) answer;
		} catch (ClassCastException e) {
			throw new ParseAnswerException();
		}
	}

}
