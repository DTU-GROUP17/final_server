package exceptions.auth;

public class JWTException extends RuntimeException{

	public JWTException() {
	}

	public JWTException(String message) {
		super(message);
	}
}
