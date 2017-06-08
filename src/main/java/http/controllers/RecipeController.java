package http.controllers;

import annotations_.http.Authenticated;
import annotations_.http.PATCH;
import app.App;
import models.api.schemas.IngredientSchema;
import models.api.schemas.RecipeSchema;
import models.api.schemas.Schema;
import models.db.Recipe;
import models.db.User;
import models.db.Weight;
import models.mappers.RecipeMapper;
import models.mappers.UserMapper;
import models.mappers.WeightMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
	public Response show(@PathParam("recipeId") String recipeId) {
		return Response.serverError().build();
	}

	@POST
	public Response create(RecipeSchema schema) {
        try (Session session = App.factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Recipe recipe = RecipeMapper.INSTANCE.RecipeSchemaToRecipe(schema);
			System.out.println("recipe = " + recipe);
			session.persist(recipe);
            transaction.commit();
            return Response.ok().build();
        }
	}

	@DELETE
	@Path("{recipeId: \\d+}")
	public Response delete(@PathParam("recipeId") String recipeId) {
		return Response.serverError().build();
	}
}
