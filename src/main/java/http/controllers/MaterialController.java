package http.controllers;

import annotations_.http.Authenticated;
import annotations_.http.PATCH;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("materials")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"Foreman"})
public class MaterialController {

	@GET
	public Response index() {
		return Response.serverError().build(); //TODO: create
	}

	@GET
	@Path("{foremanId: \\d+}")
	public Response show(@PathParam("foremanId") String foremanId) {
		return Response.serverError().build(); //TODO: create
	}

	@POST
	public Response create() {
		return Response.serverError().build(); //TODO: create
	}
}
