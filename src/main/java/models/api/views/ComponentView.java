package models.api.views;

import lombok.Data;
import models.api.views.Basic.BasicUserView;

import java.sql.Timestamp;

import static models.api.views.View.Type.Component;

@Data
public class ComponentView implements View{
	private String name;
	private Integer id;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Timestamp deletedAt;
	private BasicUserView createdBy;
	private BasicUserView updatedBy;
	private BasicUserView deletedBy;

	@Override
	public Type getType() {
		return Component;
	}
}
