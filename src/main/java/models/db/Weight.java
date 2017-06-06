package models.db;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Entity
@Table(name = "weights")
public class Weight {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Basic@Column(name = "name", nullable = false)
	private String name;

	@Basic@Column(name = "uri", nullable = false)
	private String uri;

	@Basic@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

	@Basic@Column(name = "updated_at", nullable = false)
	private Timestamp updatedAt;

	@Basic@Column(name = "deleted_at", nullable = false)
	private Timestamp deletedAt;

	@OneToMany(mappedBy = "weight")
	private Collection<Weighing> weighings;

	@ManyToOne@JoinColumn(name = "created_by", referencedColumnName = "id")
	private User createdBy;

	@ManyToOne@JoinColumn(name = "updated_by", referencedColumnName = "id")
	private User updatedBy;

	@ManyToOne@JoinColumn(name = "deleted_by", referencedColumnName = "id")
	private User deletedBy;

}
