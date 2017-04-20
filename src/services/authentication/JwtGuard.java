package services.authentication;

import app.App;
import exceptions.auth.InvalidCredentials;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import models.User;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

@Accessors(chain = true)
public class JwtGuard implements Guard<JwtGuard>{

	private String jwtToken;

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
		try(Session session = App.factory.openSession()) {

			List<Authenticatable> users;

			users = session
				.createQuery("from User where userName=:username")
					.setParameter("username", identifier)
						.list();

			if (users.size() > 0) {
				return users.get(0);
			} else {
				throw new InvalidCredentials();
			}
		}
	}

	public JwtGuard generateToken() {
		this.jwtToken = Jwts.builder()
				.setSubject(this.user.getIdentifier())
					.signWith(SignatureAlgorithm.HS512, App.key)
						.compact();
		return this;
	}

	public String getToken() {
		return this.jwtToken;
	}
}
