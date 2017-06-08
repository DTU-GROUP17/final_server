package http.controllers;

import annotations_.http.Authenticated;
import annotations_.http.PATCH;
import models.api.schemas.IngredientSchema;
import models.api.schemas.RecipeSchema;
import models.api.schemas.Schema;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.AbstractCollection;
import java.util.HashSet;

@Path("recipes")
//@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RolesAllowed({"Pharmaceud"})
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
	public Response create(RecipeSchema schema) {

        HashSet<IngredientSchema> ingredients = (HashSet<IngredientSchema>) schema.getIngredients();

        System.out.println(ingredients);


        return Response.serverError().build(); //TODO: create
	}

	@DELETE
	@Path("{recipeId: \\d+}")
	public Response delete(@PathParam("recipeId") String recipeId) {
		return Response.serverError().build(); //TODO: create
	}
}
