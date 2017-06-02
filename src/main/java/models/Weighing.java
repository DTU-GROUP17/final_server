package models;

import lombok.*;

import javax.persistence.*;


@Data
@Entity
@Table(name = "weighings")
public class Weighing {
	@Id@Column(name = "id", nullable = false)
	private int id;

	@Basic@Column(name = "amount")
	private Double amount;

	@ManyToOne@JoinColumn(name = "product_batch_id", referencedColumnName = "id", nullable = false)
	private ProductBatch productBatch;

	@ManyToOne@JoinColumn(name = "material_id", referencedColumnName = "id", nullable = false)
	private Material material;

	@ManyToOne@JoinColumn(name = "weight_id", referencedColumnName = "id", nullable = false)
	private Weight weight;
}
