package http.controllers;

import annotations_.http.Authenticated;
import annotations_.http.PATCH;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("recipes")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"Pharmaceud"})
public class RecipeController {

	@GET
	public Response index() {
		return Response.serverError().build(); //TODO: create
	}

	@GET
	@Path("{recipeId: \\d+}")
	public Response show(@PathParam("recipeId") String recipeId) {
		return Response.serverError().build(); //TODO: create
	}

	@POST
	public Response create() {
		return Response.serverError().build(); //TODO: create

	}

	@DELETE
	@Path("{recipeId: \\d+}")
	public Response delete(@PathParam("recipeId") String recipeId) {
		return Response.serverError().build(); //TODO: create
	}
}
