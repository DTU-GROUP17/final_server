package exceptions.model;


import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ModelNotFoundException extends RuntimeException implements ExceptionMapper<ModelNotFoundException> {
	@Override
	public Response toResponse(ModelNotFoundException exception) {
		return Response.status(Response.Status.NOT_FOUND).entity("The given model was not found.").build();
	}
}
