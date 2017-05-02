package http.middleware;

import annotations.http.Authenticated;
import exceptions.auth.InvalidCredentials;
import exceptions.auth.JWTException;
import services.authentication.Guard;
import services.authentication.JwtGuard;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Priority(Priorities.AUTHENTICATION)
@Authenticated
@Provider
public class Authentication implements ContainerRequestFilter {

	private final javax.inject.Provider<Guard> guardProvider;

	@Inject
	public Authentication(javax.inject.Provider<Guard> guardProvider) {
		this.guardProvider = guardProvider;
	}


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
			JwtGuard guard = (JwtGuard) this.guardProvider.get();
			guard.setToken(authorization);

			request.setSecurityContext(guard);

		} catch (JWTException|InvalidCredentials e) {
			// If the signature is invalid, then we respond with an unauthorized.
			throw new NotAuthorizedException("Invalid JWT token.");
		}

	}


}
