package models.api.schemas;

import java.util.Set;

public class SelfSchema implements Schema {
	private String name;
	private String username;
	private String password;
	private Set<Integer> roles;
}
