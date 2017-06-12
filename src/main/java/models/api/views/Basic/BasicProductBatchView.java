package models.api.views.Basic;

import lombok.Data;
import models.api.views.View;

import java.sql.Timestamp;

@Data
public class BasicProductBatchView implements View {
	private Integer id;
	private BasicRecipeView recipe;
	private String status;

	@Override
	public Type getType() {
		return Type.ProductBatch;
	}
}
