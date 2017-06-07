package models.mappers;

import models.api.schemas.UserSchema;
import models.api.views.Basic.BasicUserView;
import models.api.views.UserView;
import models.db.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = RoleMapper.class)
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mappings({
	})
	UserView UserToUserView(User user);

	List<UserView> UsersToUserViews(List<User> users);

	BasicUserView UserToBasicUserView(User user);

	@Mappings({
	})
	User UserSchemaToUser(UserSchema schema);

}
