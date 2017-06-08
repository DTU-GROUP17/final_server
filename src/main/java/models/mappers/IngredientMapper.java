package models.mappers;

import models.api.schemas.IngredientSchema;
import models.api.views.IngredientView;
import models.db.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;


@Mapper(uses = ComponentMapper.class)
public interface IngredientMapper {
    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

    IngredientView IngredientToIngredientView(Ingredient ingredient);

    List<IngredientView> IngredientsToIngredientViews(List<Ingredient> ingredients);

    Set<Ingredient> IngredientSchemasToIngredients(Set<IngredientSchema> schemas);

    Ingredient IngredientSchemaToIngredient(IngredientSchema schema);


}
