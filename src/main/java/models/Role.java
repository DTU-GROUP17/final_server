package models;

import app.App;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends Model{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	@Getter private int id;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy = "roles")
	@Getter @Setter private Set<User> users = new HashSet<>(0);

	@Column(nullable = false)
	@Getter @Setter private String name;

	public static Set<Role> getRolesFomNames(Session session, Set<String> roles){
		if (roles.isEmpty())
			return new HashSet<>();
		return new HashSet<>(
				session
					.createQuery("from Role where name in (:roles)")
						.setParameterList("roles", roles)
							.list()
		);
	}

	public static Set<Role> getRoleFromNames(Set<String> roles){
		try (Session session = App.factory.openSession()) {
			return Role.getRolesFomNames(session, roles);
		}
	}

}
