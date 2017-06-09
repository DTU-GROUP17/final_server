package exceptions.model;

import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.HashMap;
import java.util.Map;

public class PersistenceExceptionMapper implements ExceptionMapper<PersistenceException> {
	@Override
	public Response toResponse(PersistenceException exception) {

		Map<String, String> map = new HashMap<>();
		map.put("exception", exception.getClass().getName());
		map.put("message", exception.getMessage());

		return Response.notModified().entity(map).build();
	}
}
