package models.mappers;

import models.api.schemas.UserSchema;
import models.api.views.UserView;
import models.db.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(uses = RoleMapper.class)
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mappings({
	})
	UserView UserToUserView(User user);

	List<UserView> UsersToUserViews(List<User> users);

	@Mappings({
	})
	User UserSchemaToUser(UserSchema schema);

}
