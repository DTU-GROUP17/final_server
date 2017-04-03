package http.middleware;

import annotations.http.Authenticated;
import app.App;
import io.jsonwebtoken.*;

import javax.annotation.Priority;
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
			Jwts.parser().setSigningKey(App.key).parseClaimsJws(authorization);
		} catch (ExpiredJwtException|UnsupportedJwtException|MalformedJwtException|SignatureException|IllegalArgumentException e) {
			// If the signature is invalid, then we respond with an unauthorized.
			throw new NotAuthorizedException("Invalid JWT token.");
		}
	}
}
