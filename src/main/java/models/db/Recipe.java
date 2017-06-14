package models.db;

import lombok.*;
import lombok.experimental.Accessors;
import models.db.timestamps.UserCreateable;
import models.db.timestamps.UserSoftDeleteable;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString(of = {})
@Entity
@Where(clause = "deleted_at IS NULL")
@Table(name = "recipes")
public class Recipe extends Model implements UserCreateable<Recipe>, UserSoftDeleteable<Recipe> {

	@Basic@Column(name = "name", nullable = false)
	private String name;

	@Basic
	@CreationTimestamp
	@Column(name = "created_at")
	protected Timestamp createdAt;

	@ManyToOne@JoinColumn(name = "created_by", referencedColumnName = "id")
	protected User createdBy;

	@Basic@Column(name = "deleted_at")
	private Timestamp deletedAt;

	@ManyToOne@JoinColumn(name = "deleted_by", referencedColumnName = "id")
	private User deletedBy;

	@OneToMany(mappedBy = "recipe")
	private Collection<ProductBatch> productBatches;

	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "recipe_id")
	private Set<Ingredient> ingredients;

}
