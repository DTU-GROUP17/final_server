package models.db;

import lombok.*;
import lombok.experimental.Accessors;
import models.db.timestamps.UserCreateable;
import models.db.timestamps.UserSoftDeleteable;
import models.db.timestamps.UserUpdateable;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString(of = {})
@Where(clause = "deleted_at IS NULL")
@Entity
@Table(name = "suppliers")
public class Supplier extends Model implements UserCreateable<Supplier>, UserUpdateable<Supplier>, UserSoftDeleteable<Supplier> {

	@Basic@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(mappedBy = "supplier")
	private Collection<Material> materials;

	@Basic
	@CreationTimestamp
	@Column(name = "created_at")
	protected Timestamp createdAt;

	@ManyToOne@JoinColumn(name = "created_by", referencedColumnName = "id")
	protected User createdBy;

	@Basic@Column(name = "updated_at")
	private Timestamp updatedAt;

	@Basic@Column(name = "deleted_at")
	private Timestamp deletedAt;

	@ManyToOne@JoinColumn(name = "updated_by", referencedColumnName = "id")
	private User updatedBy;

	@ManyToOne@JoinColumn(name = "deleted_by", referencedColumnName = "id")
	private User deletedBy;

}
