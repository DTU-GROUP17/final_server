package models.api.views.Basic;

import models.api.views.View;

public class BasicRoleView implements View{
	private String name;
	private Integer id;

	@Override
	public View.Type getType() {
		return View.Type.Role;
	}
}
