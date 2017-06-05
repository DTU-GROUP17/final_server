package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;
import services.authentication.Authenticatable;
import services.hash.Hasher;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends Model implements Authenticatable<User>{
	@Id@Column(name = "id", nullable = false)
	private int id;

	@Basic@Column(name = "name", nullable = false)
	private String name;

	@Basic@Column(name = "username", nullable = false)
	private String username;

	@JsonIgnore
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

	@ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
	private Set<Role> roles;

	@JsonIgnore
	@Override
	public String getIdentifier() {
		return this.username;
	}


	public User setPassword(String password) {
		this.password = Hasher.hash(password);
		return this;
	}
}
