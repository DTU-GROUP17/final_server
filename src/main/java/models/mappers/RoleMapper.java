package models.mappers;

import models.api.views.RoleView;
import models.db.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface RoleMapper {

	RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

	RoleView RoleToRoleView(Role role);

//	RoleView RoleToRoleView(Role role);

	Set<RoleView> RolesToRoleViews(Set<Role> roles);


}
