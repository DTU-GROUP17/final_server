package models;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Entity
@Table(name = "components")
public class Component {

	@Id@Column(name = "id", nullable = false)
	private int id;

	@Basic@Column(name = "name", nullable = false)
	private String name;

	@Basic@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

	@Basic@Column(name = "deleted_at", nullable = false)
	private Timestamp deletedAt;

	@ManyToOne@JoinColumn(name = "created_by", referencedColumnName = "id")
	private User createdBy;

	@ManyToOne@JoinColumn(name = "deleted_by", referencedColumnName = "id")
	private User deletedBy;

	@OneToMany(mappedBy = "components")
	private Collection<Material> materials;

}
