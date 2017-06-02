package models;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Entity
@Table(name = "recipes")
public class Recipe {
	@Id@Column(name = "id", nullable = false)
	private int id;

	@Basic@Column(name = "name", nullable = false)
	private String name;

	@Basic@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

	@Basic@Column(name = "deleted_at", nullable = false)
	private Timestamp deletedAt;

	@OneToMany(mappedBy = "Recipe")
	private Collection<ProductBatch> productBatches;

	@ManyToOne@JoinColumn(name = "created_by", referencedColumnName = "id")
	private User createdBy;

	@ManyToOne@JoinColumn(name = "deleted_by", referencedColumnName = "id")
	private User deletedBy;

}
