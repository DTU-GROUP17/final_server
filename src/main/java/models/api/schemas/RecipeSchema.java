package models.api.schemas;

import lombok.Data;
import models.db.Ingredient;

import java.util.Set;

@Data
public class RecipeSchema implements Schema {
    private String name;
    private Set<IngredientSchema> ingredients;
}
