package controller.users;

import annotation.PATCH;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("self")
@Produces("application/json")
public class SelfController {

	@GET
	public Response getSelf() {
		return Response.ok("found it!").build();
	}

	@PATCH
	public Response updateSelf() {
		return Response.noContent().build();
	}
}
