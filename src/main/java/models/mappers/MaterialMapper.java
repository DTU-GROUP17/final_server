package models.mappers;

import models.api.schemas.MaterialSchema;
import models.api.views.MaterialView;
import models.db.Component;
import models.db.Material;
import models.db.Supplier;
import models.db.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {UserMapper.class, SupplierMapper.class, ComponentMapper.class})
public interface MaterialMapper {

	MaterialMapper INSTANCE = Mappers.getMapper(MaterialMapper.class);

	Material MaterialSchemaToMaterial(MaterialSchema schema);

	MaterialView MaterialToMaterialView(Material material);

	List<MaterialView> MaterialsToMaterialViews(List<Material> materials);

}
