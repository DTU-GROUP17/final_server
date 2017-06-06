package models.mappers;

import models.api.schemas.UserSchema;
import models.api.views.UserView;
import models.db.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = RoleMapper.class)
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mappings({
	})
	UserView UserToUserView(User user);

	@Mappings({
	})
	User UserSchemaToUser(UserSchema schema);

}
