package models.api.views.Basic;

import lombok.Data;
import models.api.views.View;

@Data
public class BasicRecipeView implements View {
	private Integer id;
	private String name;

	@Override
	public Type getType() {
		return Type.Recipe;
	}
}
