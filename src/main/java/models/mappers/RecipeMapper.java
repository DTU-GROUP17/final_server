package models.mappers;

import models.api.schemas.RecipeSchema;
import models.api.views.RecipeView;
import models.db.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(uses = {IngredientMapper.class, UserMapper.class})
public interface RecipeMapper {
    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    RecipeView RecipeToRecipeView(Recipe recipe);

    List<RecipeView> RecipesToRecipeViews(List<Recipe> recipes);

    Recipe RecipeSchemaToRecipe(RecipeSchema schema);

    @Mapping(target = "id", expression = "java(integer)")
    Recipe IntegerToRecipe(Integer integer);

}
