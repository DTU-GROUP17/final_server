package models.mappers;

import models.api.views.Basic.BasicRoleView;
import models.api.views.RoleView;
import models.db.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper
public interface RoleMapper {

	RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

	RoleView RoleToRoleView(Role role);

	List<RoleView> RolesToRoleViews(List<Role> roles);

	BasicRoleView RoleToBasicRoleView(Role role);

	Set<BasicRoleView> RolesToBasicRoleViews(Set<Role> roles);

	@Mapping(target = "id", expression = "java(integer)")
	Role IntegerToRole(Integer integer);

	Set<Role> IntegersToRoles(Set<Role> roles);

}
