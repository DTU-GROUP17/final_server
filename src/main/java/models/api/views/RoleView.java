package models.api.views;

import lombok.Data;
import models.db.User;

import java.sql.Timestamp;

@Data
public class RoleView implements View {
	private String name;
	private Integer id;
	private Timestamp createdAt;
	private User createdBy;
	private Timestamp updatedAt;
	private User updatedBy;
	private Timestamp deletedAt;
	private User deletedBy;

	@Override
	public String getType() {
		return "role";
	}
}
