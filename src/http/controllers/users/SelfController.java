package http.controllers.users;

import annotations.http.PATCH;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("self")
@Produces("application/json")
public class SelfController {

	@GET
	public Response show() {
		return Response.ok("found it!").build();
	}

	@PATCH
	public Response update() {
		return Response.noContent().build();
	}
}
