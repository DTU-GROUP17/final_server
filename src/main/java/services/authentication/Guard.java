package services.authentication;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public interface Guard<SELF extends Guard<SELF>> extends SecurityContext {
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

	@Override
	default Principal getUserPrincipal() {
		return this.getUser();
	}

	@Override
	default boolean isUserInRole(String role) {
		return this.getUser().hasRole(role);
	}

	@Override
	default boolean isSecure() {
		return true;
	}

	@Override
	default String getAuthenticationScheme() {
		return "Bearer";
	}
}
