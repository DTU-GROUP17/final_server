package models.mappers;

import models.api.schemas.ProductBatchSchema;
import models.api.views.Basic.BasicProductBatchView;
import models.api.views.ProductBatchView;
import models.db.ProductBatch;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {RecipeMapper.class, UserMapper.class})
public interface ProductBatchMapper {
	ProductBatchMapper INSTANCE = Mappers.getMapper(ProductBatchMapper.class);

	ProductBatch ProductBatchSchemaToProductBatch(ProductBatchSchema schema);

	ProductBatchView ProductBatchToProductBatchView(ProductBatch productBatch);

	List<ProductBatchView> ProductBatchesToProductBatchViews(List<ProductBatch> productBatches);

	BasicProductBatchView ProductBatchToBasicProductBatchView(ProductBatch productBatch);

}
