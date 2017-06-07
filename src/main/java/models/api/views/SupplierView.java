package models.api.views;

import models.api.views.Basic.BasicUserView;

import java.sql.Timestamp;

import static models.api.views.View.Type.Supplier;

public class SupplierView implements View {
	private String name;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Timestamp deletedAt;
	private BasicUserView createdBy;
	private BasicUserView updatedBy;
	private BasicUserView deletedBy;

	@Override
	public Type getType() {
		return Supplier;
	}
}
