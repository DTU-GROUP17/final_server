package models.mappers;

import models.api.schemas.SupplierSchema;
import models.db.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SupplierUpdater {

	SupplierUpdater INSTANCE = Mappers.getMapper(SupplierUpdater.class);

	void updateSupplierFromSupplierSchema(SupplierSchema schema, @MappingTarget Supplier supplier);

}
