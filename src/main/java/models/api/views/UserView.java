package models.api.views;

import lombok.Data;
import models.db.User;

import java.sql.Timestamp;
import java.util.Set;

@Data
public class UserView implements View {
	private String name;
	private String username;
	private Integer id;
	private Set<RoleView> roles;
	private Timestamp createdAt;
	private User createdBy;
	private Timestamp updatedAt;
	private User updatedBy;
	private Timestamp deletedAt;
	private User deletedBy;

	@Override
	public String getType() {
		return "user";
	}
}
