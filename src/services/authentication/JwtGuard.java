package services.authentication;

import app.App;
import exceptions.auth.InvalidCredentials;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.Session;

@Accessors(chain = true)
public class JwtGuard implements Guard<JwtGuard>{

	private Class<? extends Authenticatable> model;

	public JwtGuard(@NonNull Class<? extends Authenticatable> model) {
		this.model = model;
	}

	@Getter @Setter Authenticatable user;

	@Override
	public boolean check() {
		return user != null;
	}

	@Override
	public boolean isGuest() {
		return !check();

	}

	@Override
	public boolean validate(@NonNull String identifier, @NonNull String password) {
		try {
			this.user = this.retrieveByCredentials(identifier, password);
			return true;
		} catch (InvalidCredentials invalidCredentials) {
			return false;
		}
	}

	private Authenticatable retrieveByCredentials(String identifier, String password) throws InvalidCredentials {
		Session session = App.factory.openSession();
		Authenticatable user = session.find(this.model, identifier);

		if(!user.validateCredentials(password)) {
			throw new InvalidCredentials();
		}

		return user;
	}

}
