package models.db;

import lombok.*;
import lombok.experimental.Accessors;
import models.db.timestamps.UserCreateable;
import models.db.timestamps.UserSoftDeleteable;
import models.db.timestamps.UserUpdateable;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import services.authentication.Authenticatable;
import services.hash.Hasher;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true, of = {})
@Entity
@Table(name = "users")
public class User extends Model implements UserCreateable<User>, UserUpdateable<User>, UserSoftDeleteable<User>, Authenticatable<User> {

	@Basic@Column(name = "name", nullable = false)
	@Getter@Setter private String name;

	@Basic@Column(name = "username", nullable = false)
	@Getter@Setter private String username;

	@Basic@Column(name = "password")
	@Getter private String password;

	@Basic
	@CreationTimestamp
	@Column(name = "created_at")
	@Getter@Setter protected Timestamp createdAt;

	@ManyToOne@JoinColumn(name = "created_by", referencedColumnName = "id")
	@Getter@Setter protected User createdBy;

	@Basic
	@Column(name = "updated_at")
	@Getter@Setter private Timestamp updatedAt;

	@Basic
	@Column(name = "deleted_at")
	@Getter@Setter private Timestamp deletedAt;

	@ManyToOne@JoinColumn(name = "updated_by", referencedColumnName = "id")
	@Getter@Setter private User updatedBy;

	@ManyToOne@JoinColumn(name = "deleted_by", referencedColumnName = "id")
	@Getter@Setter private User deletedBy;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "users_roles",
		inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false),
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	)
	@Getter@Setter private Set<Role> roles;

	@Override
	public String getIdentifier() {
		return this.getUsername();
	}

	public User setPassword(String password) {
		this.password = Hasher.hash(password);
		return this;
	}

}
