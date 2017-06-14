package models.db;

import lombok.*;
import lombok.experimental.Accessors;
import models.db.timestamps.UserCreateable;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true, of = {})
@Entity
@Table(name = "materials")
public class Material extends Model implements UserCreateable<Material>{

	public Material() {
		this.setUsed(0.0);
	}

	@Basic
	@CreationTimestamp
	@Column(name = "created_at")
	@Getter@Setter protected Timestamp createdAt;

	@ManyToOne@JoinColumn(name = "created_by", referencedColumnName = "id")
	@Getter@Setter protected User createdBy;

	@Basic@Column(name = "stocked", nullable = false)
	@Getter@Setter private Double stocked;

	@Basic@Column(name = "used", nullable = false)
	@Getter@Setter private Double used;

	@ManyToOne@JoinColumn(name = "component_id", referencedColumnName = "id", nullable = false)
	@Getter@Setter private Component component;

	@ManyToOne@JoinColumn(name = "supplier_id", referencedColumnName = "id", nullable = false)
	@Getter@Setter private Supplier supplier;

	@OneToMany(mappedBy = "material")
	@Getter@Setter private Collection<Weighing> weighings;

	public Double getInStock() {
		return this.getStocked() - this.getUsed();
	}

	public void take(Double amount) throws MaterialException {
		if (
			amount < 0.0
			|| amount > this.getInStock()
		) {
			throw new MaterialException();
		}
		this.setUsed(this.getUsed() + amount);
	}

}
