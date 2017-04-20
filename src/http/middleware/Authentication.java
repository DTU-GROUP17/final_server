package http.middleware;

import annotations.http.Authenticated;
import app.App;
import exceptions.auth.InvalidCredentials;
import io.jsonwebtoken.*;
import models.User;
import org.hibernate.Session;
import services.authentication.Authenticatable;

import javax.annotation.Priority;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;
import java.util.List;


@Priority(Priorities.AUTHENTICATION)
@Authenticated
@Provider
public class Authentication implements ContainerRequestFilter {


	public void filter(ContainerRequestContext request) throws IOException {
		// extract the authorization header from the request.
		String authorization = request.getHeaderString(HttpHeaders.AUTHORIZATION);

		// We start by looking if we got an valid authorization header.
		if(authorization == null || !authorization.startsWith("Bearer ")) {
			throw new NotAuthorizedException("Invalid JWT authorization header.");
		}

		// If valid header, then we extract the token from the `Bearer`.
		authorization = authorization.substring("Bearer ".length()).trim();

		try {
			// We check if the key is parsing the signing.
			Jws<Claims>  jws = Jwts.parser().setSigningKey(App.key).parseClaimsJws(authorization);

			final SecurityContext currentSecurityContext = request.getSecurityContext();

			request.setSecurityContext(new SecurityContext() {

				@Override
				public Principal getUserPrincipal() {

					return () -> jws.getBody().getSubject();
				}

				@Override
				public boolean isUserInRole(String role) {
					try(Session session = App.factory.openSession()) {

						List<User> users;
						users = session
								.createQuery("from User where userName=:username")
								.setParameter("username", this.getUserPrincipal().getName())
								.list();

						return users.size() == 1 && users.get(0).getRoles().stream().anyMatch(role1 -> role1.getName().equals(role));
					}
					catch (Exception e) {
						return false;
					}
				}

				@Override
				public boolean isSecure() {
					return currentSecurityContext.isSecure();
				}

				@Override
				public String getAuthenticationScheme() {
					return "Bearer";
				}
			});

		} catch (ExpiredJwtException|UnsupportedJwtException|MalformedJwtException|SignatureException|IllegalArgumentException e) {
			// If the signature is invalid, then we respond with an unauthorized.
			throw new NotAuthorizedException("Invalid JWT token.");
		}

	}


}
