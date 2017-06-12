package models.db;

import lombok.*;
import lombok.experimental.Accessors;
import models.db.timestamps.UserCreateable;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString(of = {})
@Entity
@Table(name = "product_batches")
public class ProductBatch extends Model implements UserCreateable<ProductBatch> {

	public enum Status {
		QUEUED,
		ACTIVE,
		COMPLETE
	}

	@Basic
	@CreationTimestamp
	@Column(name = "created_at")
	protected Timestamp createdAt;

	@ManyToOne@JoinColumn(name = "created_by", referencedColumnName = "id")
	protected User createdBy;

	@Basic@Column(name = "status", nullable = false)
	private Status status;

	@ManyToOne
	@JoinColumn(name = "recipe_id", referencedColumnName = "id", nullable = false)
	private Recipe recipe;

	@OneToMany(mappedBy = "productBatch")
	private Set<Weighing> weighings;
}
