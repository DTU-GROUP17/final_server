package models.db;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import models.db.timestamps.UserCreateable;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString(of = {})
@Entity
@Table(name = "materials")
public class Material extends Model implements UserCreateable<Material>{

	@Basic
	@CreationTimestamp
	@Column(name = "created_at")
	protected Timestamp createdAt;

	@ManyToOne@JoinColumn(name = "created_by", referencedColumnName = "id")
	protected User createdBy;

	@Basic@Column(name = "stocked", nullable = false)
	private Double stocked;

	@Basic@Column(name = "used", nullable = false)
	private Double used;

	@ManyToOne@JoinColumn(name = "component_id", referencedColumnName = "id", nullable = false)
	private Component component;

	@ManyToOne@JoinColumn(name = "supplier_id", referencedColumnName = "id", nullable = false)
	private Supplier supplier;

	@OneToMany(mappedBy = "material")
	private Collection<Weighing> weighings;

}
