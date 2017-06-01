package models;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ingredients")
@IdClass(IngredientPK.class)
public class Ingredient {
	@Id@Column(name = "recipe_id", nullable = false)
	private int recipeId;
	@Id@Column(name = "component_id", nullable = false)
	private int componentId;
	@Basic@Column(name = "amount", nullable = true, precision = 0)
	private Double amount;
	@Basic@Column(name = "tolerence", nullable = true, precision = 0)
	private Double tolerence;

}
