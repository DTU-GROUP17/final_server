package models.db;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "ingredients")
public class Ingredient extends Model {
//	@Column(name = "recipe_id", nullable = false)
//	private Integer recipeId;



	@Basic@Column(name = "amount")
	private Double amount;

	@Basic@Column(name = "tolerance")
	private Double tolerance;


    @ManyToOne
    @JoinColumn(name = "recipe_id", referencedColumnName = "id", nullable = false)
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "component_id", referencedColumnName = "id", nullable = false)
    private Component component;




}
