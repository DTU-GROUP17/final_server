package models.api.views;

import lombok.Data;
import models.api.views.Basic.BasicRecipeView;
import models.api.views.Basic.BasicUserView;
import models.api.views.Basic.BasicWeighingView;
import models.api.views.Basic.BasicWeightView;
import models.db.Weighing;

import java.sql.Timestamp;
import java.util.Set;

@Data
public class ProductBatchView implements View {
	private Integer id;
	private BasicRecipeView recipe;
	private String status;
//	private Set<BasicWeighingView> weighings;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Timestamp deletedAt;
	private BasicUserView createdBy;
	private BasicUserView updatedBy;
	private BasicUserView deletedBy;

	@Override
	public Type getType() {
		return Type.ProductBatch;
	}
}
