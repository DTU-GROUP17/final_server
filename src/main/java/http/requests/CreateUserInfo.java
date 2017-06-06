package http.requests;

import java.util.Set;

public class CreateUserInfo {
	private String name;
	private String username;
	private String password;
	private Set<String> roles;
	public boolean isFull(){
		return this.getName()!=null && this.getUsername()!=null && this.getPassword()!=null && this.getRoles()!=null;
	}

	public String getName() {
		return this.name;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public Set<String> getRoles() {
		return this.roles;
	}
}
