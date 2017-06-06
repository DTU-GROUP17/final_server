package http.controllers;

import annotations_.http.Authenticated;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("roles")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoleController {

	@GET
	public Response index() {
		return Response.serverError().build(); //TODO: create
	}

	@GET
	@Path("{roleId: \\d+}")
	public Response show(@PathParam("roleId") String roleId) {
		return Response.serverError().build(); //TODO: create
	}
}
