package models.mappers;

import models.api.schemas.MaterialSchema;
import models.api.views.Basic.BasicMaterialView;
import models.api.views.MaterialView;
import models.db.Material;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {UserMapper.class, SupplierMapper.class, ComponentMapper.class})
public interface MaterialMapper {

	MaterialMapper INSTANCE = Mappers.getMapper(MaterialMapper.class);

//	@Mapping(target = "used", expression = "java(0.0)")
	Material MaterialSchemaToMaterial(MaterialSchema schema);

//	@Mapping(target = "inStock", expression = "java(material.getStocked()-material.getUsed())")
	MaterialView MaterialToMaterialView(Material material);

	List<MaterialView> MaterialsToMaterialViews(List<Material> materials);

	BasicMaterialView MaterialToBasicMaterialView(Material material);
}
