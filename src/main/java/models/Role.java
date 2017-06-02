package models;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Entity
@Table(name = "roles")
public class Role {

	@Id@Column(name = "id", nullable = false)
	private int id;

	@Basic@Column(name = "name", nullable = false)
	private String name;

	@Basic@Column(name = "created_at", nullable = false)
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

	@ManyToMany@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	)
	private Collection<User> users;

}
