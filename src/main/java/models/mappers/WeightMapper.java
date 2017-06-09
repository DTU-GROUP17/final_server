package models.mappers;

import models.api.schemas.WeightSchema;
import models.api.views.WeightView;
import models.db.Weight;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = UserMapper.class)
public interface WeightMapper {
	WeightMapper INSTANCE = Mappers.getMapper(WeightMapper.class);

	WeightView WeightToWeightView(Weight weight);

	List<WeightView> WeightsToWeightViews(List<Weight> weights);

	Weight WeightSchemaToWeight(WeightSchema schema);

}
