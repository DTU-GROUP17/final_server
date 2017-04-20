package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import services.authentication.Authenticatable;
import services.hash.Hasher;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Accessors(chain = true)
public class User extends Model implements Authenticatable<User> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	@Getter private int id;

	@ManyToMany(fetch = FetchType.EAGER)
	@Cascade(value = CascadeType.ALL)
	@JoinTable(
		name = "users_roles",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	@Getter @Setter private Set<Role> roles;


	@Column(nullable = false)
	@Getter @Setter private String name;

	@Column(nullable = false)
	@Getter private String password;

	@Column(nullable = false, unique = true)
	@Getter @Setter private String userName;

	@JsonIgnore
	public User setPassword(String password) {
		this.password = Hasher.hash(password);
		return this;
	}

	@JsonIgnore
	@Override
	public String getIdentifier() {
		return this.getUserName();
	}

}
