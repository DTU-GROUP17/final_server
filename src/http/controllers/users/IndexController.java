package http.controllers.users;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("")
@Produces("application/json")
public class IndexController {

	@GET
	public Response show() {
		return Response.ok("indexing").build();
	}
}
