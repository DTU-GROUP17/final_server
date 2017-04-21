package services.authentication;

import app.App;
import exceptions.auth.InvalidCredentials;
import exceptions.auth.JWTException;
import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import models.User;
import org.hibernate.Session;
import java.util.List;

@Accessors(chain = true)
public class JwtGuard implements Guard<JwtGuard>{

	@Getter private String jwtToken;

	private Class<? extends Authenticatable> model;

	public JwtGuard(@NonNull Class<? extends Authenticatable> model) {
		this.model = model;
	}

	public JwtGuard() {
		this.model = User.class;
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

	public JwtGuard setToken(String token) throws JWTException, InvalidCredentials {
		return this.setJwtToken(token);
	}

	public JwtGuard setJwtToken(String jwtToken) throws JWTException, InvalidCredentials {
		// Validate the token.
		try {
			Jws<Claims> jws = Jwts.parser()
					.setSigningKey(App.key)
					.parseClaimsJws(jwtToken);

			// Fetch the user matching the token.
			try(Session session = App.factory.openSession()) {
				List<User> users = session
						.createQuery("from User where userName=:username")
						.setParameter("username", jws.getBody().getSubject())
						.list();

				if(!users.isEmpty()) {
					this.setUser(users.get(0));
				}
				else {
					// If the user does not exist.
					throw new InvalidCredentials();
				}
			}

			this.jwtToken = jwtToken;
		}
		catch (SignatureException|ExpiredJwtException|UnsupportedJwtException|MalformedJwtException|IllegalArgumentException exception) {
			throw new JWTException(exception.getMessage());
		}

		return this;
	}
}
