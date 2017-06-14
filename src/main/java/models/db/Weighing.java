package models.db;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;

@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true, of = {})
@Entity
@Table(name = "weighings")
public class Weighing extends Model{

	@Basic@Column(name = "amount")
	@Getter@Setter private Double amount;

	@ManyToOne@JoinColumn(name = "product_batch_id", nullable = false)
	@Getter@Setter private ProductBatch productBatch;

	@ManyToOne@JoinColumn(name = "material_id", referencedColumnName = "id", nullable = false)
	@Getter@Setter private Material material;

	@ManyToOne@JoinColumn(name = "weight_id", referencedColumnName = "id", nullable = false)
	@Getter@Setter private Weight weight;

	@Basic@Column(name = "weighed_at", nullable = false)
	@Getter@Setter private Timestamp weighedAt;

	@ManyToOne@JoinColumn(name = "weighed_by", referencedColumnName = "id")
	@Getter@Setter private User weighedBy;

}
