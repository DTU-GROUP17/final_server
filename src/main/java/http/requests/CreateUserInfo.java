package http.requests;

import lombok.Getter;

import java.util.Set;

public class CreateUserInfo {
	@Getter private String name;
	@Getter private String username;
	@Getter private String password;
	@Getter private Set<String> roles;
	public boolean isFull(){
		return this.getName()!=null && this.getUsername()!=null && this.getPassword()!=null && this.getRoles()!=null;
	}
}
