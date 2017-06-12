package models.api.views.Basic;

import models.api.views.View;

public class BasicWeighingView implements View {
	private Integer id;

	@Override
	public Type getType() {
		return Type.Weighing;
	}
}
