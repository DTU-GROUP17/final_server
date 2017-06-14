package models.db;

import lombok.*;
import lombok.experimental.Accessors;
import models.db.timestamps.UserCreateable;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true, of = {})
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
	@Getter@Setter private Timestamp createdAt;

	@ManyToOne@JoinColumn(name = "created_by", referencedColumnName = "id")
	@Getter@Setter private User createdBy;

	@Basic@Column(name = "status", nullable = false)
	@Getter@Setter private Status status;

	@ManyToOne
	@JoinColumn(name = "recipe_id", referencedColumnName = "id", nullable = false)
	@Getter@Setter private Recipe recipe;

	@OneToMany(mappedBy = "productBatch")
	@Getter@Setter private Set<Weighing> weighings;

}
