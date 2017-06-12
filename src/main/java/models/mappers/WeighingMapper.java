package models.mappers;

import models.api.views.Basic.BasicWeighingView;
import models.api.views.WeighingView;
import models.db.Weighing;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(uses = {MaterialMapper.class, WeightMapper.class, UserMapper.class})
public interface WeighingMapper {

	WeighingMapper INSTANCE = Mappers.getMapper(WeighingMapper.class);

	WeighingView WeighingToWeighingView(Weighing weighing);

	List<WeighingView> WeighingsToWeighingViews(List<Weighing> weighing);

	BasicWeighingView WeighingToBasicWeighingView(Weighing weighing);

	Set<BasicWeighingView> WeighingsToBasicWeighingViews(Set<Weighing> weighings);

}
