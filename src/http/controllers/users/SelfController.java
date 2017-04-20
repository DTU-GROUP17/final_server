package http.controllers.users;

import annotations.http.Authenticated;
import annotations.http.PATCH;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("self")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
public class SelfController {

	@GET
	public Response show(@Context SecurityContext securityContext) {
		return Response.ok("found it!").build();
	}

	@PATCH
	public Response update() {
		return Response.noContent().build();
	}
}
