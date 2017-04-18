package services.authentication;

public interface Guard<SELF extends Guard<SELF>> {
	/**
	 * Determine if the current user is authenticated.
	 */
	boolean check();

	/**
	 * Determine if the current user is a guest.
	 */
	boolean isGuest();

	/**
	 * Get the currently authenticated user.
	 */
	Authenticatable getUser();

	/**
	 * Set the current user.
	 */
	SELF setUser(Authenticatable user);

	/**
	 * Validate a user's credentials.
	 */
	boolean validate(String identifier, String password);


}
