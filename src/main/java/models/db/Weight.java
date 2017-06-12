package models.db;

import lombok.*;
import lombok.experimental.Accessors;
import models.db.timestamps.UserCreateable;
import models.db.timestamps.UserSoftDeleteable;
import models.db.timestamps.UserUpdateable;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
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
@Table(name = "weights")
public class Weight extends Model implements UserCreateable<Weight>, UserUpdateable<Weight>, UserSoftDeleteable<Weight> {

	@Basic@Column(name = "name", nullable = false)
	private String name;

	@Basic@Column(name = "uri", nullable = false)
	private String uri;

	@Basic
	@CreationTimestamp
	@Column(name = "created_at")
	protected Timestamp createdAt;

	@ManyToOne@JoinColumn(name = "created_by", referencedColumnName = "id")
	protected User createdBy;

	@Basic
	@UpdateTimestamp
	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@Basic @Column(name = "deleted_at")
	private Timestamp deletedAt;

	@OneToMany(mappedBy = "weight")
	private Collection<Weighing> weighings;

	@ManyToOne@JoinColumn(name = "updated_by", referencedColumnName = "id")
	private User updatedBy;
	
	@ManyToOne@JoinColumn(name = "deleted_by", referencedColumnName = "id")
	private User deletedBy;

}
