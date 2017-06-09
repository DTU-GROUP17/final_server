package models.db;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString(of = {})
@Entity
//@SQLDelete(sql = "SELECT * FROM ingredients")
@Table(name = "ingredients")
public class Ingredient extends Model {

	@Basic@Column(name = "amount")
	private Double amount;

	@Basic@Column(name = "tolerance")
	private Double tolerance;

    @ManyToOne
    @JoinColumn(name = "component_id", nullable = false)
    private Component component;

//    @ManyToOne()
//	private Recipe recipe;

}
