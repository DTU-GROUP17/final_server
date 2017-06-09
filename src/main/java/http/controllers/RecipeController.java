package http.controllers;

import app.App;
import lombok.Getter;
import models.api.schemas.RecipeSchema;
import models.db.Recipe;
import models.mappers.RecipeMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.authentication.Guard;
import services.response.ApiResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("recipes")
//@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RolesAllowed({"Pharmaceud"})
public class RecipeController implements Controller {

	@Context
	@Getter
	public Guard guard;

	@GET
	public Response index() {
        try (Session session = App.factory.openSession()) {
            return Response.ok(
				RecipeMapper.INSTANCE.RecipesToRecipeViews(
					session.createQuery("FROM Recipe").list()
				)
            ).build();
        }
	}

	@GET
	@Path("{recipeId: \\d+}")
	public Response show(@PathParam("recipeId") String id) {
		try (Session session = App.factory.openSession()) {
			return ApiResponse.item(
				RecipeMapper.INSTANCE.RecipeToRecipeView(
					session.find(Recipe.class, Integer.parseInt(id))
				)
			).build();
		}
	}

	@POST
	public Response create(RecipeSchema schema) {
        try (Session session = App.factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Recipe recipe = RecipeMapper.INSTANCE.RecipeSchemaToRecipe(schema);
			session.persist(recipe);
            transaction.commit();
            return Response.ok().build();
        }
	}

	@DELETE
	@Path("{recipeId: \\d+}")
	public Response delete(@PathParam("recipeId") String id) {
		return this.delete(Recipe.class, id);
	}
}
