package models.db;

import lombok.*;
import lombok.experimental.Accessors;
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
@Entity
@SQLDelete(sql = "UPDATE weights SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
@Table(name = "weights")
public class Weight extends Model implements SoftDeletable<Weight>, Updateable<Weight> {

	@Basic@Column(name = "name", nullable = false)
	private String name;

	@Basic@Column(name = "uri", nullable = false)
	private String uri;

	@Basic
	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Timestamp updatedAt;

	@Basic @Column(name = "deleted_at", nullable = false)
	private Timestamp deletedAt;

	@OneToMany(mappedBy = "weight")
	private Collection<Weighing> weighings;

	@ManyToOne@JoinColumn(name = "updated_by", referencedColumnName = "id")
	private User updatedBy;
	
	@ManyToOne@JoinColumn(name = "deleted_by", referencedColumnName = "id")
	private User deletedBy;

}
