package database.seeds;

import database.HibernateMigration;
import models.api.schemas.IngredientSchema;
import models.api.schemas.RecipeSchema;
import models.db.Ingredient;
import models.db.Recipe;
import models.mappers.RecipeMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class V3_6__seed_recipes extends HibernateMigration {

	private void createRecipe(Session session, RecipeSchema schema){
		Recipe recipe = RecipeMapper.INSTANCE.RecipeSchemaToRecipe(schema);
		session.persist(recipe);
	}

	@Override
	protected void migrate(Session session) throws Exception {
		Transaction transaction = session.beginTransaction();
		IngredientSchema ingredient_1 = new IngredientSchema();
		ingredient_1.setComponent(1);
		ingredient_1.setAmount(10.0);
		ingredient_1.setAmount(20.5);
		IngredientSchema ingredient_2 = new IngredientSchema();
		ingredient_2.setComponent(2);
		ingredient_2.setAmount(2.4);
		ingredient_2.setTolerance(45.1);
		Set<IngredientSchema> ingredients = new HashSet<>();
		ingredients.add(ingredient_1);
		ingredients.add(ingredient_2);
		RecipeSchema schema = new RecipeSchema();
		schema.setName("Pizza");
		schema.setIngredients(ingredients);
		this.createRecipe(session, schema);
		transaction.commit();
	}
}
