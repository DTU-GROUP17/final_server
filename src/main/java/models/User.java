package models;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Entity
@Table(name = "users")
public class User {
	@Id@Column(name = "id", nullable = false)
	private int id;

	@Basic@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Basic@Column(name = "username", nullable = false, length = 255)
	private String username;

	@Basic@Column(name = "password", nullable = false, length = 255)
	private String password;

	@Basic@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

	@Basic@Column(name = "updated_at", nullable = false)
	private Timestamp updatedAt;

	@Basic@Column(name = "deleted_at", nullable = false)
	private Timestamp deletedAt;

	@ManyToMany(mappedBy = "users")
	private Collection<Role> roles;

}
