package models.api.views;

public interface View {
	Type getType();

	enum Type {
		User,
		Weight,
		Role,
		Supplier,
		Component,
		Material
		Supplier,
		Recipe
	}
}
