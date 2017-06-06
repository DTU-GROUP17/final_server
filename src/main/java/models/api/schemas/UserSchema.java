package models.api.schemas;

import lombok.Data;

import java.util.Set;

@Data
public class UserSchema implements Schema{
	private String name;
	private String username;
	private String password;
	private Set<String> roles;
}
