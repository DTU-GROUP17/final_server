package models.db;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Entity
@Table(name = "roles")
public class Role extends Model {

	@Basic@Column(name = "name", nullable = false)
	private String name;

	@Basic
	@CreationTimestamp
	@Setter(AccessLevel.NONE)
	@Column(name = "created_at")
	private Timestamp createdAt;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	private Collection<User> users;

	public String toString(){
		return "Role Object";
	}

}
