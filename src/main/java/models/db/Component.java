package models.db;

import lombok.*;
import lombok.experimental.Accessors;
import models.db.timestamps.UserCreateable;
import models.db.timestamps.UserSoftDeleteable;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true, of = {})
@Entity
@Table(name = "components")
public class Component extends Model implements UserCreateable<Component>, UserSoftDeleteable<Component>{

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

	@ManyToOne
	@JoinColumn(name = "deleted_by", referencedColumnName = "id")
	@Getter@Setter private User deletedBy;

	@OneToMany(mappedBy = "component")
	@Getter@Setter private Collection<Material> materials;

}
