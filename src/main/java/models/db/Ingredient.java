package models.db;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "ingredients")
public class Ingredient extends Model {

	@Basic@Column(name = "amount")
	private Double amount;

	@Basic@Column(name = "tolerance")
	private Double tolerance;

    @ManyToOne
    @JoinColumn(name = "component_id", referencedColumnName = "id", nullable = false)
    private Component component;

//    @ManyToOne
//	@JoinColumn(name = "recipe_id", referencedColumnName = "id")
//	private Recipe recipe;

}
