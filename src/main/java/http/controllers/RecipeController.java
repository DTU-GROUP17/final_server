package http.controllers;

import annotations.http.Authenticated;
import lombok.Getter;
import models.api.schemas.RecipeSchema;
import models.db.Recipe;
import models.mappers.RecipeMapper;
import services.authentication.Guard;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("recipes")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"Pharmaceud"})
public class RecipeController implements Controller {

	@Context
	@Getter
	public Guard guard;

	@GET
	public Response index() {
		return this.collection(RecipeMapper.INSTANCE::RecipesToRecipeViews, Recipe.class);
	}

	@GET
	@Path("{recipeId: \\d+}")
	public Response show(@PathParam("recipeId") String id) {
		return this.item(RecipeMapper.INSTANCE::RecipeToRecipeView, Recipe.class, id);
	}

	@POST
	public Response create(RecipeSchema schema) {
		return this.create(RecipeMapper.INSTANCE::RecipeSchemaToRecipe, schema);
	}

	@DELETE
	@Path("{recipeId: \\d+}")
	public Response delete(@PathParam("recipeId") String id) {
		return this.delete(Recipe.class, id);
	}
}
