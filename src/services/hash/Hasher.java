package services.hash;

import org.mindrot.jbcrypt.BCrypt;

public class Hasher {

	public static boolean check(String value, String hashedValue) {
		return BCrypt.checkpw(value, hashedValue);
	}

	public static String hash(String value) {
		return BCrypt.hashpw(value, BCrypt.gensalt());
	}
}
