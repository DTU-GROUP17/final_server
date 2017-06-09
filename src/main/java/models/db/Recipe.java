package models.db;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString(of = {})
@Entity
@SQLDelete(sql = "UPDATE recipes SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Table(name = "recipes")
public class Recipe extends Model implements SoftDeletable<Recipe> {

	@Basic@Column(name = "name", nullable = false)
	private String name;

	@Basic@Column(name = "deleted_at")
	private Timestamp deletedAt;

	@OneToMany(mappedBy = "recipe")
	private Collection<ProductBatch> productBatches;

	@ManyToOne@JoinColumn(name = "deleted_by", referencedColumnName = "id")
	private User deletedBy;

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "recipe_id")
//	@JoinTable(
//		name="recipes_ingredients",
//		joinColumns = @JoinColumn(
//			name="recipe_id"
//		),
//		inverseJoinColumns = @JoinColumn(
//			name="ingredient_id"
//		)
//	)
	private Set<Ingredient> ingredients;



}
