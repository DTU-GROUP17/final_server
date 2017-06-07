package models.mappers;

import models.api.schemas.SelfSchema;
import models.api.schemas.UserSchema;
import models.db.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = RoleMapper.class, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserUpdater {

	UserUpdater INSTANCE = Mappers.getMapper(UserUpdater.class);

	void updateUserFromUserSchema(UserSchema schema, @MappingTarget User user);

	void updateUserFromSelfSchema(SelfSchema schema, @MappingTarget User user);

}
