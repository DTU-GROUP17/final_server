package exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExcpetionCatcher implements ExceptionMapper<Throwable> {
	@Override
	public Response toResponse(Throwable exception) {
		System.out.println("exception = " + exception);
		return Response.serverError().build();
	}
}
