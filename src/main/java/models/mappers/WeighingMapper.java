package models.mappers;

import models.api.views.WeighingView;
import models.db.Weighing;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ProductBatchMapper.class, MaterialMapper.class, WeightMapper.class})
public interface WeighingMapper {

	WeighingMapper INSTANCE = Mappers.getMapper(WeighingMapper.class);

	WeighingView WeighingToWeighingView(Weighing weighing);

	List<WeighingView> WeighingsToWeighingViews(List<Weighing> weighing);

}
