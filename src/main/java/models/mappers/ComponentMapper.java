package models.mappers;


import models.api.schemas.ComponentSchema;
import models.api.views.Basic.BasicComponentView;
import models.api.views.ComponentView;
import models.db.Component;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = UserMapper.class )
public interface ComponentMapper {

	ComponentMapper INSTANCE = Mappers.getMapper(ComponentMapper.class);

	Component ComponentSchemaToComponent (ComponentSchema schema);

	ComponentView ComponentToComponentView(Component component);

	List<ComponentView> CompoinentsToComponentViews(List<Component> components);

	BasicComponentView ComponentToBasicComponent(Component component);

	@Mapping(target = "id", expression = "java(integer)")
	Component IntegerToComponent(Integer integer);

}
