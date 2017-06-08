package models.mappers;

import models.api.schemas.WeightSchema;
import models.api.views.WeightView;
import models.db.Ingredient;
import models.db.Weight;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by durankose on 08/06/2017.
 */
@Mapper
public interface IngredientMapper {
    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

    @Mappings({
    })
    IngredientView WeightToWeightView(Weight weight);

    List<WeightView> WeightsToWeightViews(List<Weight> weights);

    @Mappings({
    })
    Weight WeightSchemaToWeight(WeightSchema schema);
}
