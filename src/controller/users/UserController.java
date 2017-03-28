package controller.users;


import annotation.PATCH;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("users/{userId: [0-9]+}")
@Produces("application/json")
public class UserController {

	@PathParam("userId")
	private String userId;

	@GET
	public Response getUser() {
		if(userId.equals("10"))
			return Response.ok("found it!").build();
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@PATCH
	public Response updateUser() {
		return Response.noContent().build();
	}
}
