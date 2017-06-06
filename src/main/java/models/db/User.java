package models.db;

import lombok.Data;
import lombok.experimental.Accessors;
import services.authentication.Authenticatable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

//@EqualsAndHashCode(of = "id", callSuper = false)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "users")
public class User extends Model implements Authenticatable<User>{

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Basic@Column(name = "name", nullable = false)
	private String name;

	@Basic@Column(name = "username", nullable = false)
	private String username;

	@Basic@Column(name = "password")
	private String password;

	@Basic
	@Column(
			name = "created_at",
			insertable = false,
			columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP"
	)
	private Timestamp createdAt;

	@Basic@Column(name = "updated_at")
	private Timestamp updatedAt;

	@Basic@Column(name = "deleted_at")
	private Timestamp deletedAt;

	@ManyToOne@JoinColumn(name = "created_by", referencedColumnName = "id")
	private User createdBy;

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
		return this.username;
	}

	public String toString(){
		return "User Object";
	}

}
