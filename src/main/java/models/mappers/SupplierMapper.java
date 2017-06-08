package models.mappers;

import models.api.schemas.SupplierSchema;
import models.api.views.Basic.BasicSupplierView;
import models.api.views.SupplierView;
import models.db.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = UserMapper.class)
public interface SupplierMapper {

	SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);

	Supplier SupplierSchemaToSupplier(SupplierSchema schema);

	SupplierView SupplierToSupplierView(Supplier supplier);

	List<SupplierView> SuppliersToSupplierViews(List<Supplier> suppliers);

	BasicSupplierView SupplierToBasicSupplierView(Supplier supplier);

	@Mapping(target = "id", expression = "java(integer)")
	Supplier IntegerToSupplier(Integer integer);

}
