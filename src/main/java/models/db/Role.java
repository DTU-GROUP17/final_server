package models.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

//@EqualsAndHashCode(of = "id")
@Data
@Entity
@Table(name = "roles")
public class Role {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Basic@Column(name = "name", nullable = false)
	private String name;

	@Basic
	@Column(
			name = "created_at",
			insertable = false,
			columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP"
	)
	private Timestamp createdAt;

	@Basic@Column(name = "updated_at", nullable = false)
	private Timestamp updatedAt;

	@Basic@Column(name = "deleted_at", nullable = false)
	private Timestamp deletedAt;

	@ManyToOne@JoinColumn(name = "created_by", referencedColumnName = "id")
	private User createdBy;

	@ManyToOne@JoinColumn(name = "updated_by", referencedColumnName = "id")
	private User updatedBy;

	@ManyToOne@JoinColumn(name = "deleted_by", referencedColumnName = "id")
	private User deletedBy;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	private Collection<User> users;

	public String toString(){
		return "Role Object";
	}

}
