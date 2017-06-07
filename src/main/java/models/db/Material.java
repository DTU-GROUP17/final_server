package models.db;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "materials")
public class Material extends Model {

	@Basic@Column(name = "stocked", nullable = false)
	private double stocked;

	@Basic@Column(name = "used")
	private Double used;

	@ManyToOne@JoinColumn(name = "component_id", referencedColumnName = "id", nullable = false)
	private Component components;

	@ManyToOne@JoinColumn(name = "supplier_id", referencedColumnName = "id", nullable = false)
	private Supplier suppliers;

	@OneToMany(mappedBy = "material")
	private Collection<Weighing> weighings;

}
