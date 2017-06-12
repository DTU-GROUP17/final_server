package models.api.views;

import lombok.Data;
import models.api.views.Basic.BasicMaterialView;
import models.api.views.Basic.BasicProductBatchView;
import models.api.views.Basic.BasicUserView;
import models.api.views.Basic.BasicWeightView;

import java.sql.Timestamp;

@Data
public class WeighingView implements View {
	private Integer id;
	private BasicMaterialView material;
	private BasicWeightView weight;
	private Double amount;
	private Timestamp weighedAt;
	private BasicUserView weighedBy;

	@Override
	public Type getType() {
		return Type.Weighing;
	}
}
