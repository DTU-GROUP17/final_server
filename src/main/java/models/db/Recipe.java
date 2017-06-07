package models.db;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "recipes")
public class Recipe extends Model implements SoftDeletable<Recipe> {

	@Basic@Column(name = "name", nullable = false)
	private String name;

	@Basic@Column(name = "deleted_at", nullable = false)
	private Timestamp deletedAt;

	@OneToMany(mappedBy = "recipe")
	private Collection<ProductBatch> productBatches;

	@ManyToOne@JoinColumn(name = "deleted_by", referencedColumnName = "id")
	private User deletedBy;

}
