package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends Model{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	@Getter
	private int id;

	@ManyToMany(cascade=CascadeType.ALL, mappedBy = "roles")
	@Getter @Setter private Set<User> users = new HashSet<>(0);


	@Column(nullable = false)
	@Getter @Setter private String name;
}
