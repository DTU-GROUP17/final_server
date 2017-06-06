package http.controllers;

import annotations_.http.Authenticated;
import annotations_.http.PATCH;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("components")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"Pharmaceud"})
public class ComponentController {

	@GET
	public Response index() {
		return Response.serverError().build(); //TODO: create
	}

	@GET
	@Path("{componentId: \\d+}")
	public Response show(@PathParam("componentId") String componentId) {
		return Response.serverError().build(); //TODO: create
	}

	@POST
	public Response create() {
		return Response.serverError().build(); //TODO: create
	}

	@DELETE
	@Path("{componentId: \\d+}")
	public Response delete(@PathParam("componentId") String componentId) {
		return Response.serverError().build(); //TODO: create
	}
}
