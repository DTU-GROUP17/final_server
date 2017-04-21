package http.controllers.users;

import annotations.http.Authenticated;
import annotations.http.PATCH;
import services.authentication.Guard;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Authenticated
@Path("self")
@Produces(MediaType.APPLICATION_JSON)
public class SelfController {

	@GET
	@RolesAllowed({"admin"})
	public Response show(@Context Guard guard) {
		return Response.ok(guard.getUser()).build();
	}

	@PATCH
	public Response update() {
		return Response.noContent().build();
	}
}
