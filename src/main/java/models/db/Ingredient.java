package models.db;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Entity
@EqualsAndHashCode(callSuper = true, of = {})
@Table(name = "ingredients")
public class Ingredient extends Model {

	@Basic@Column(name = "amount", nullable = false)
	@Getter@Setter private Double amount;

	@Basic@Column(name = "tolerance", nullable = false)
	@Getter@Setter private Double tolerance;

    @ManyToOne
    @JoinColumn(name = "component_id", nullable = false)
	@Getter@Setter private Component component;

    public double getUpperLimit() {
    	return this.getAmount() * (100 + this.getTolerance()) / 100;
	}

	public double getLowerLimit() {
		return this.getAmount() * (100 - this.getTolerance()) / 100;
	}

}
