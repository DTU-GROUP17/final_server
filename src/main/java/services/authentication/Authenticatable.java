package services.authentication;

import models.Role;
import services.hash.Hasher;

import java.security.Principal;
import java.util.Set;

public interface Authenticatable<T extends Authenticatable<T>> extends Principal {

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

	T setRoles(Set<Role> roles);

	Set<Role> getRoles();

	default boolean hasRole(Role role) {
		return this.getRoles().contains(role);
	}

	default boolean hasRole(String role) {
		return this.getRoles().stream().anyMatch(role1 -> role1.getName().equals(role));
	}
}
