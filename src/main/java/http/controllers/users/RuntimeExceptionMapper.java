package http.controllers.users;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<Throwable> {

	private static final long serialVersionUID = 1L;

	@Override
	public Response toResponse(Throwable exception) {
		System.out.println(Arrays.toString(exception.getStackTrace()));
		Map<String, String> map = new HashMap<>();
		map.put("exception", exception.getClass().getName());
		map.put("message", exception.getMessage());
		return Response.status(500).entity(map).type(MediaType.APPLICATION_JSON_TYPE).build();
	}

}