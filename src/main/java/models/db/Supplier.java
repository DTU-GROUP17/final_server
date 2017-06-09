package models.db;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Entity
@Table(name = "suppliers")
public class Supplier extends Model implements SoftDeletable<Supplier>, Updateable<Supplier> {

	@Basic@Column(name = "name", nullable = false)
	private String name;

	@Basic@Column(name = "updated_at", nullable = false)
	private Timestamp updatedAt;

	@Basic@Column(name = "deleted_at", nullable = false)
	private Timestamp deletedAt;

	@OneToMany(mappedBy = "supplier")
	private Collection<Material> materials;

	@ManyToOne@JoinColumn(name = "updated_by", referencedColumnName = "id")
	private User updatedBy;

	@ManyToOne@JoinColumn(name = "deleted_by", referencedColumnName = "id")
	private User deletedBy;

}
