package models.db;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Entity
@Table(name = "suppliers")
public class Supplier extends Model{

//	@Id
//	@Column(name = "id", nullable = false)
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;

	@Basic@Column(name = "name", nullable = false)
	private String name;

	@Basic@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

	@Basic@Column(name = "updated_at", nullable = false)
	private Timestamp updatedAt;

	@Basic@Column(name = "deleted_at", nullable = false)
	private Timestamp deletedAt;

	@OneToMany(mappedBy = "suppliers")
	private Collection<Material> materials;

	@ManyToOne@JoinColumn(name = "created_by", referencedColumnName = "id")
	private User createdBy;

	@ManyToOne@JoinColumn(name = "updated_by", referencedColumnName = "id")
	private User updatedBy;

	@ManyToOne@JoinColumn(name = "deleted_by", referencedColumnName = "id")
	private User deletedBy;

}
