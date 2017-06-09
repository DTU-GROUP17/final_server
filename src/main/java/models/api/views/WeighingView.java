package models.api.views;

import lombok.Data;
import models.api.views.Basic.BasicMaterialView;
import models.api.views.Basic.BasicProductBatchView;
import models.api.views.Basic.BasicWeightView;

@Data
public class WeighingView implements View {
	private Integer id;
	private BasicProductBatchView productBatch;
	private BasicMaterialView material;
	private BasicWeightView weight;
	private Double amount;

	@Override
	public Type getType() {
		return Type.Weighing;
	}
}
