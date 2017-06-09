package exceptions.auth;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidCredentials extends Exception implements ExceptionMapper<InvalidCredentials> {
	public InvalidCredentials() {

	}

	@Override
	public Response toResponse(InvalidCredentials exception) {
		return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build();
	}
}
