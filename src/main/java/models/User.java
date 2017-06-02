package models;

import lombok.*;
import services.authentication.Authenticatable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User implements Authenticatable<User>{
	@Id@Column(name = "id", nullable = false)
	private int id;

	@Basic@Column(name = "name", nullable = false)
	private String name;

	@Basic@Column(name = "username", nullable = false)
	private String username;

	@Basic@Column(name = "password", nullable = false)
	private String password;

	@Basic@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

	@Basic@Column(name = "updated_at", nullable = false)
	private Timestamp updatedAt;

	@Basic@Column(name = "deleted_at", nullable = false)
	private Timestamp deletedAt;

	@ManyToMany(mappedBy = "users")
	private Collection<Role> roles;

	@Override
	public String getIdentifier() {
		return null;
	}

	@Override
	public User setRoles(Set<Role> roles) {
		return null;
	}

	@Override
	public Set<Role> getRoles(){
		return null;
	}

	@Override
	public String getPassword(){
		return null;
	}

	@Override
	public User setPassword(String password){
		return this;
	}

}
