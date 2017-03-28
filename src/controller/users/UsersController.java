package controller.users;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("users")
public class UsersController {

	@GET
	@Produces("application/json")
	public String getUsers() {
		return "all users";
	}
}
