package models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	@Getter private int id;



	@ManyToMany()
	@Cascade(value = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns =@JoinColumn(name = "user_id"), inverseJoinColumns =@JoinColumn(name = "role_id"))
	@Getter @Setter private Set<Role> roles;


	@Column(nullable = false)
	@Getter @Setter private String name;


}
