package models.db;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ingredients")
@IdClass(IngredientKey.class)
public class Ingredient {

	@Id
	@Column(name = "recipe_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer recipeId;

	@Id@Column(name = "component_id", nullable = false)
	private Integer componentId;

	@Basic@Column(name = "amount")
	private Double amount;

	@Basic@Column(name = "tolerence")
	private Double tolerence;

}
