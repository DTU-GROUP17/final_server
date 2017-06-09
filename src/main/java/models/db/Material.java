package models.db;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Entity
@Table(name = "materials")
public class Material extends Model {

	@Basic@Column(name = "stocked", nullable = false)
	private double stocked;

	@Basic@Column(name = "used")
	private Double used;

	@ManyToOne@JoinColumn(name = "component_id", referencedColumnName = "id", nullable = false)
	private Component component;

	@ManyToOne@JoinColumn(name = "supplier_id", referencedColumnName = "id", nullable = false)
	private Supplier supplier;

	@OneToMany(mappedBy = "material")
	private Collection<Weighing> weighings;

}
