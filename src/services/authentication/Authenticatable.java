package services.authentication;

import services.hash.Hasher;

public interface Authenticatable<T extends Authenticatable<T>> {


	/**
	 * Get the unique identifier for the user.
	 */
	String getIdentifier();

	/**
	 * Get the password for the user.
	 */
	String getPassword();

	T setPassword(String password);

	default boolean validateCredentials(String password) {
		return Hasher.check(password, this.getPassword());
	}
}
