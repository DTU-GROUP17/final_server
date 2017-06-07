package models.api.views;

import lombok.Data;
import models.db.User;

import java.sql.Timestamp;

@Data
public class RoleView implements View {
	private String name;
	private Integer id;
	private Timestamp createdAt;

	@Override
	public Type getType() {
		return Type.Role;
	}
}
