package exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class DeleteItemException
	extends RuntimeException
		implements ExceptionMapper<exceptions.model.ModelNotFoundException>
{
	@Override
	public Response toResponse(exceptions.model.ModelNotFoundException exception) {
		return Response.status(Response.Status.NOT_FOUND)
				.entity("Unable to delete user.")
					.build();
	}
}
