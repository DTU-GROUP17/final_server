package models.api.views;

import lombok.Data;
import models.api.views.Basic.BasicRoleView;
import models.api.views.Basic.BasicUserView;

import java.sql.Timestamp;
import java.util.Set;

@Data
public class UserView implements View {
	private String name;
	private String username;
	private Integer id;
	private Set<BasicRoleView> roles;
	private Timestamp createdAt;
	private BasicUserView createdBy;
	private Timestamp updatedAt;
	private BasicUserView updatedBy;
	private Timestamp deletedAt;
	private BasicUserView deletedBy;

	@Override
	public Type getType() {
		return Type.User;
	}
}
