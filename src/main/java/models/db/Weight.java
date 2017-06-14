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

@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true, of = {})
@Entity
@Table(name = "weights")
public class Weight extends Model implements UserCreateable<Weight>, UserUpdateable<Weight>, UserSoftDeleteable<Weight> {

	@Basic@Column(name = "name", nullable = false)
	@Getter@Setter private String name;

	@Basic@Column(name = "uri", nullable = false)
	@Getter@Setter private String uri;

	@Basic
	@CreationTimestamp
	@Column(name = "created_at")
	@Getter@Setter private Timestamp createdAt;

	@ManyToOne@JoinColumn(name = "created_by", referencedColumnName = "id")
	@Getter@Setter private User createdBy;

	@Basic
	@UpdateTimestamp
	@Column(name = "updated_at")
	@Getter@Setter private Timestamp updatedAt;

	@Basic @Column(name = "deleted_at")
	@Getter@Setter private Timestamp deletedAt;

	@OneToMany(mappedBy = "weight")
	@Getter@Setter private Collection<Weighing> weighings;

	@ManyToOne@JoinColumn(name = "updated_by", referencedColumnName = "id")
	@Getter@Setter private User updatedBy;
	
	@ManyToOne@JoinColumn(name = "deleted_by", referencedColumnName = "id")
	@Getter@Setter private User deletedBy;

}
