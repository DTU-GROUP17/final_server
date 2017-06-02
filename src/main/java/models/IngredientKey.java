package models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;


@Data
public class IngredientKey implements Serializable {

	@Column(name = "recipe_id", nullable = false)@Id
	private int recipeId;

	@Column(name = "component_id", nullable = false)@Id
	private int componentId;

}
