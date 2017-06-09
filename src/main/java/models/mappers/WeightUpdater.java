package models.mappers;

import models.api.schemas.WeightSchema;
import models.db.Weight;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = UserMapper.class, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface WeightUpdater {
	WeightUpdater INSTANCE = Mappers.getMapper(WeightUpdater.class);

	void updateWeightFromWeightSchema(WeightSchema schema, @MappingTarget Weight weight);

}
