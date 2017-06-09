package models.db;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Entity
@Table(name = "product_batches")
public class ProductBatch extends Model {

	enum Status {
		QUEUED,
		ACTIVE,
		COMPLETE
	}

	@Basic@Column(name = "status", nullable = false)
	private Status status;

	@Basic@Column(name = "weighed_at", nullable = false)
	private Timestamp weighedAt;

	@ManyToOne
	@JoinColumn(name = "recipe_id", referencedColumnName = "id", nullable = false)
	private Recipe recipe;

	@ManyToOne@JoinColumn(name = "weighed_by", referencedColumnName = "id")
	private User weighedBy;

	@OneToMany(mappedBy = "productBatch")
	private Collection<Weighing> weighings;
}
