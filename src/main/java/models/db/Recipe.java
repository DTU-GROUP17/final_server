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
import java.util.List;
import java.util.Set;

@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true, of = {})
@Entity
@Table(name = "recipes")
public class Recipe extends Model implements UserCreateable<Recipe>, UserSoftDeleteable<Recipe> {

	@Basic@Column(name = "name", nullable = false)
	@Getter@Setter private String name;

	@Basic
	@CreationTimestamp
	@Column(name = "created_at")
	@Getter@Setter private Timestamp createdAt;

	@ManyToOne@JoinColumn(name = "created_by", referencedColumnName = "id")
	@Getter@Setter private User createdBy;

	@Basic@Column(name = "deleted_at")
	@Getter@Setter private Timestamp deletedAt;

	@ManyToOne@JoinColumn(name = "deleted_by", referencedColumnName = "id")
	@Getter@Setter private User deletedBy;

	@OneToMany(mappedBy = "recipe")
	@Getter@Setter private Collection<ProductBatch> productBatches;

	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "recipe_id")
	@Getter@Setter private List<Ingredient> ingredients;

}
