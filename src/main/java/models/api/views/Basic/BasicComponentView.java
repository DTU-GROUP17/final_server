package models.api.views.Basic;

import lombok.Data;
import models.api.views.View;

@Data
public class BasicComponentView implements View {

	private String name;
	private Integer id;

	@Override
	public Type getType() {
		return Type.Component;
	}

}
