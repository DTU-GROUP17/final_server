package models.db;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString(of = {})
@Entity
@Table(name = "weighings")
public class Weighing extends Model{

	@Basic@Column(name = "amount")
	private Double amount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_batch_id", referencedColumnName = "id")
	private ProductBatch productBatch;

	@ManyToOne@JoinColumn(name = "material_id", referencedColumnName = "id", nullable = false)
	private Material material;

	@ManyToOne@JoinColumn(name = "weight_id", referencedColumnName = "id", nullable = false)
	private Weight weight;

	@Basic@Column(name = "weighed_at", nullable = false)
	private Timestamp weighedAt;

	@ManyToOne@JoinColumn(name = "weighed_by", referencedColumnName = "id")
	private User weighedBy;

}
