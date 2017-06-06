package http.controllers;

import annotations_.http.Authenticated;
import annotations_.http.PATCH;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("weights")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"Admin"})
public class WeightController {

	@GET
	public Response index() {
		return Response.serverError().build(); //TODO: create
	}

	@GET
	@Path("{weightId: \\d+}")
	public Response show(@PathParam("weightId") String weightId) {
		return Response.serverError().build(); //TODO: create
	}

	@POST
	public Response create() {
		return Response.serverError().build(); //TODO: create
	}

	@PATCH
	@Path("{weightId: \\d+}")
	public Response update(@PathParam("weightId") String weightId) {
		return Response.serverError().build(); //TODO: create
	}

	@DELETE
	@Path("{weightId: \\d+}")
	public Response delete(@PathParam("weightId") String weightId) {
		return Response.serverError().build(); //TODO: create
	}
}
