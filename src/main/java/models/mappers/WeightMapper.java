package models.mappers;

import models.api.schemas.WeightSchema;
import models.api.views.WeightView;
import models.db.Weight;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface WeightMapper {
	WeightMapper INSTANCE = Mappers.getMapper(WeightMapper.class);

	@Mappings({
	})
	WeightView WeightToWeightView(Weight weight);

	@Mappings({
	})
	Weight WieghtSchemaToWeight(WeightSchema schema);

}
