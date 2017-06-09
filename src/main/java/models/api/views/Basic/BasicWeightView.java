package models.api.views.Basic;

import lombok.Data;
import models.api.views.View;

@Data
public class BasicWeightView implements View {
	private Integer id;
	private String name;
	private String uri;

	@Override
	public Type getType() {
		return Type.Weight;
	}
}
