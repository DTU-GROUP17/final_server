package models;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "user")
public class User extends Model {

	@Id
	@GeneratedValue
	@Column(nullable = false, unique = true)
	private int id;

	private ArrayList<Role> roles;

	private String name;

}
