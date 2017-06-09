package models.api.views;

import lombok.Data;
import models.api.views.Basic.BasicRecipeView;
import models.api.views.Basic.BasicUserView;

import java.sql.Timestamp;

@Data
public class ProductBatchView implements View {
	private Integer id;
	private BasicRecipeView recipe;
	private String status;
	private BasicUserView weighedBy;
	private Timestamp weighedAt;
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
