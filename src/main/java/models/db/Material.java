package models.db;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Entity
@Table(name = "materials")
public class Material extends Model{

//	@Id
//	@Column(name = "id", nullable = false)
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;

	@Basic@Column(name = "stocked", nullable = false)
	private double stocked;

	@Basic@Column(name = "used")
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
