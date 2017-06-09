package models.mappers;

import models.api.schemas.UserSchema;
import models.api.views.Basic.BasicUserView;
import models.api.views.UserView;
import models.db.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.function.Consumer;

@Mapper(uses = RoleMapper.class)
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	UserView UserToUserView(User user);

	List<UserView> UsersToUserViews(List<User> users);

	BasicUserView UserToBasicUserView(User user);

	User UserSchemaToUser(UserSchema schema);

}
