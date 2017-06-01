package models;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Entity
@Table(name = "materials")
public class Material {
	@Id@Column(name = "id", nullable = false)
	private int id;
	@Basic@Column(name = "stocked", nullable = false, precision = 0)
	private double stocked;
	@Basic@Column(name = "used", nullable = true, precision = 0)
	private Double used;
	@Basic@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;
	@ManyToOne@JoinColumn(name = "component_id", referencedColumnName = "id", nullable = false)
	private Component components;
	@ManyToOne@JoinColumn(name = "supplier_id", referencedColumnName = "id", nullable = false)
	private Supplier suppliers;
	@ManyToOne@JoinColumn(name = "created_by", referencedColumnName = "id")
	private User createdBy;
	@OneToMany(mappedBy = "material")
	private Collection<Weighing> weighings;

}
