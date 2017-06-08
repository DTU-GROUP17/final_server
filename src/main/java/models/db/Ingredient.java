package models.db;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "ingredients")
public class Ingredient extends Model {

	@Column(name = "recipe_id", nullable = false)
	private Integer recipeId;


	@Column(name = "component_id", nullable = false)
	private Integer componentId;

	@Basic@Column(name = "amount")
	private Double amount;

	@Basic@Column(name = "tolerance")
	private Double tolerance;

}
