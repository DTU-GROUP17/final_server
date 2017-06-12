package models.db;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import models.db.timestamps.UserCreateable;
import models.db.timestamps.UserSoftDeleteable;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString(of = {})
@Entity
@Where(clause = "deleted_at IS NULL")
@Table(name = "components")
public class Component extends Model implements UserCreateable<Component>, UserSoftDeleteable<Component>{

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

	@ManyToOne
	@JoinColumn(name = "deleted_by", referencedColumnName = "id")
	private User deletedBy;

	@OneToMany(mappedBy = "component")
	private Collection<Material> materials;

}
