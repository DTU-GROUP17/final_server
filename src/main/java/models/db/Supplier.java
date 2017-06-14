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

@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true, of = {})
@Entity
@Table(name = "suppliers")
public class Supplier extends Model implements UserCreateable<Supplier>, UserUpdateable<Supplier>, UserSoftDeleteable<Supplier> {

	@Basic@Column(name = "name", nullable = false)
	@Getter@Setter private String name;

	@OneToMany(mappedBy = "supplier")
	@Getter@Setter private Collection<Material> materials;

	@Basic
	@CreationTimestamp
	@Column(name = "created_at")
	@Getter@Setter protected Timestamp createdAt;

	@ManyToOne@JoinColumn(name = "created_by", referencedColumnName = "id")
	@Getter@Setter protected User createdBy;

	@Basic@Column(name = "updated_at")
	@Getter@Setter private Timestamp updatedAt;

	@Basic@Column(name = "deleted_at")
	@Getter@Setter private Timestamp deletedAt;

	@ManyToOne@JoinColumn(name = "updated_by", referencedColumnName = "id")
	@Getter@Setter private User updatedBy;

	@ManyToOne@JoinColumn(name = "deleted_by", referencedColumnName = "id")
	@Getter@Setter private User deletedBy;

}
