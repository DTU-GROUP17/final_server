package exceptions.auth;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

@Provider
public class NotAuthorizedExceptionMapper implements ExceptionMapper<NotAuthorizedException>{

	@Override
	public Response toResponse(NotAuthorizedException exception) {
		Map<String, String> map = new HashMap<>();
		map.put("exception", exception.getClass().getSimpleName());
		map.put("message", "Not authorized to this resource.");
		return Response.status(Response.Status.UNAUTHORIZED).entity(map).type(MediaType.APPLICATION_JSON_TYPE).build();
	}
}
