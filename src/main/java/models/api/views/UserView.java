package models.api.views;

import lombok.Data;
import models.api.views.Basic.BasicRoleView;
import models.db.User;

import java.sql.Timestamp;
import java.util.Set;

@Data
public class UserView implements View {
	private String name;
	private String username;
	private Integer id;
	private Set<BasicRoleView> roles;
	private Timestamp createdAt;
	private User createdBy;
	private Timestamp updatedAt;
	private User updatedBy;
	private Timestamp deletedAt;
	private User deletedBy;

	@Override
	public Type getType() {
		return Type.User;
	}
}
