package services.authentication;

import models.db.Role;
import services.hash.Hasher;

import java.security.Principal;
import java.util.Arrays;
import java.util.Set;

public interface Authenticatable<T extends Authenticatable<T>> extends Principal {

	public enum Roles {
		Admin,
		Pharmaceud,
		Foreman,
		Lab_Technician;

		public int toValue() {
			switch (this) {
				case Admin: return 1;
				case Pharmaceud: return 2;
				case Foreman: return 3;
				case Lab_Technician: return 4;
				default: throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * Get the unique identifier for the user.
	 */
	String getIdentifier();

	Integer getId();

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

	default boolean hasRole(String target) {
		return this.getRoles().stream()
			.anyMatch(role -> role.getId().equals(Roles.valueOf(target).toValue()));
	}
}
