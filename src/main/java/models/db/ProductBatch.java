package models.db;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Entity
@Table(name = "product_batches")
public class ProductBatch {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Basic@Column(name = "status", nullable = false)
	private String status;

	@Basic@Column(name = "weighed_at", nullable = false)
	private Timestamp weighedAt;

	@Basic@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

	@ManyToOne
	@JoinColumn(name = "recipe_id", referencedColumnName = "id", nullable = false)
	private Recipe recipe;

	@ManyToOne@JoinColumn(name = "weighed_by", referencedColumnName = "id")
	private User weighedBy;

	@ManyToOne@JoinColumn(name = "created_by", referencedColumnName = "id")
	private User createdBy;

	@OneToMany(mappedBy = "productBatch")
	private Collection<Weighing> weighings;
}
