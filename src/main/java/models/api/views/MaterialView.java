package models.api.views;

import lombok.Data;
import models.api.views.Basic.BasicComponentView;
import models.api.views.Basic.BasicUserView;

import java.sql.Timestamp;

@Data
public class MaterialView implements View {

	private Double stocked;
	private Double used;
	private BasicComponentView component;
	private BasicUserView supplier;
	private Timestamp createdAt;
	private BasicUserView createdBy;

	@Override
	public Type getType() {
		return Type.Material;
	}
}
