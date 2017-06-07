package models.api.views.Basic;

import lombok.Data;
import models.api.views.View;

@Data
public class BasicUserView implements View {
	private Integer id;

	@Override
	public Type getType() {
		return Type.User;
	}
}
