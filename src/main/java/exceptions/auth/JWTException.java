package exceptions.auth;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class JWTException extends RuntimeException implements ExceptionMapper<JWTException>{

	public JWTException() {
	}

	public JWTException(String message) {
		super(message);
	}

	@Override
	public Response toResponse(JWTException exception) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
	}
}
