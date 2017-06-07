package models.db;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Data
@Accessors(chain = true)
@Entity
@Table(name = "weighings")
public class Weighing extends Model{

	@Basic@Column(name = "amount")
	private Double amount;

	@ManyToOne@JoinColumn(name = "product_batch_id", referencedColumnName = "id", nullable = false)
	private ProductBatch productBatch;

	@ManyToOne@JoinColumn(name = "material_id", referencedColumnName = "id", nullable = false)
	private Material material;

	@ManyToOne@JoinColumn(name = "weight_id", referencedColumnName = "id", nullable = false)
	private Weight weight;

}
