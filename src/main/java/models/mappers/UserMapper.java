package models.mappers;

import models.api.schemas.UserSchema;
import models.api.views.RoleView;
import models.api.views.UserView;
import models.db.Role;
import models.db.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mappings({
	})
	UserView UserToUserView(User user);

	@Mappings({
		@Mapping(target = "roles", ignore = true)
	})
	User UserSchemaToUser(UserSchema schema);

}
