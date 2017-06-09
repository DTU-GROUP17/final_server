package models.db;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import services.authentication.Authenticatable;
import services.hash.Hasher;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Accessors(chain = true)
@Entity
//@SQLDelete(sql = "UPDATE users SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
//@Where(clause = "deleted_at IS NULL")
@Table(name = "users")
public class User extends Model implements SoftDeletable<User>, Updateable<User>, Authenticatable<User>{

	@Basic@Column(name = "name", nullable = false)
	private String name;

	@Basic@Column(name = "username", nullable = false)
	private String username;

	@PostLoad
	public void works(){
		System.out.println("it works!");
	}

	@Basic@Column(name = "password")
	private String password;

	@Basic
	@UpdateTimestamp
	@Column(name = "updated_at")
	@Setter(AccessLevel.NONE)
	private Timestamp updatedAt;

	@Basic
	@Column(name = "deleted_at")
	@Setter(AccessLevel.NONE)
	private Timestamp deletedAt;

	@ManyToOne@JoinColumn(name = "updated_by", referencedColumnName = "id")
	private User updatedBy;

	@ManyToOne@JoinColumn(name = "deleted_by", referencedColumnName = "id")
	private User deletedBy;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "users_roles",
		inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false),
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	)
	private Set<Role> roles;

	@Override
	public String getIdentifier() {
		return this.getUsername();
	}

	public User setPassword(String password) {
		this.password = Hasher.hash(password);
		return this;
	}

	public String toString(){
		return "User Object";
	}

}
