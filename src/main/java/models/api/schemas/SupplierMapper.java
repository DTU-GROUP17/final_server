package models.api.schemas;

import models.api.views.SupplierView;
import models.db.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SupplierMapper {

	SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);

	Supplier SupplierSchemaToSupplier(SupplierSchema schema);

	SupplierView SupplierToSupplierView(Supplier supplier);

	List<SupplierView> SuppliersToSupplierViews(List<Supplier> suppliers);

}