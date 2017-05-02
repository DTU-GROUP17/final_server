package http.requests;

import lombok.Getter;

import java.util.Set;

public class CreateUserInfo {
	@Getter private String name;
	@Getter private String userName;
	@Getter private String password;
	@Getter private Set<String> roles;
	public boolean isFull(){
		return this.getName()!=null && this.getUserName()!=null && this.getPassword()!=null && this.getRoles()!=null;
	}
}
