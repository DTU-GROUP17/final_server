package http.controllers;

import annotations_.http.Authenticated;
import annotations_.http.PATCH;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("suppliers")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"Pharmaceud"})
public class SupplierController {

	@GET
	public Response index() {
		return Response.serverError().build(); //TODO: create
	}

	@GET
	@Path("{supplierId: \\d+}")
	public Response show(@PathParam("supplierId") String supplierId) {
		return Response.serverError().build(); //TODO: create
	}

	@POST
	public Response create() {
		return Response.serverError().build(); //TODO: create
	}

	@PATCH
	@Path("{supplierId: \\d+}")
	public Response update(@PathParam("supplierId") String supplierId) {
		return Response.serverError().build(); //TODO: create
	}

	@DELETE
	@Path("{supplierId: \\d+}")
	public Response delete(@PathParam("supplierId") String supplierId) {
		return Response.serverError().build(); //TODO: create
	}
}
