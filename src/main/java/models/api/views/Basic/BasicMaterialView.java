package models.api.views.Basic;

import lombok.Data;
import models.api.views.View;

@Data
public class BasicMaterialView implements View {
	private Integer id;
	private Double stocked;
	private Double inStock;
	private BasicComponentView component;
	private BasicSupplierView supplier;

	@Override
	public Type getType() {
		return Type.Material;
	}
}
