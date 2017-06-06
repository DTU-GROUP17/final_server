package http.controllers;

import annotations_.http.Authenticated;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("weighings")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"Lab Technician"})
public class WeighingController {

	@GET
	public Response index() {
		return Response.serverError().build(); //TODO: create
	}
}
