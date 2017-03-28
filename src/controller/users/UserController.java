package controller.users;


import annotation.PATCH;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("users/{userId: [0-9]+}")
public class UserController {

	@PathParam("userId")
	private String userId;

	@GET
	@Produces("application/json")
	public String getUser() {
		return "user id is "+ userId;
	}

	@PATCH
	@Produces("application/json")
	public Response updateUser() {
		return Response.noContent().build();
	}
}
