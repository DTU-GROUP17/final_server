package models;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ingredients")
@IdClass(IngredientKey.class)
public class Ingredient {

	@Id@Column(name = "recipe_id", nullable = false)
	private int recipeId;

	@Id@Column(name = "component_id", nullable = false)
	private int componentId;

	@Basic@Column(name = "amount")
	private Double amount;

	@Basic@Column(name = "tolerence")
	private Double tolerence;

}
