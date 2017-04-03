package http.controllers.users;


import annotations.http.Authenticate;
import annotations.http.PATCH;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

	@GET
	@Authenticate
	public String index() {
		return "all users";
	}

	@GET
	@Path("{userId: [0-9]+}")
	public Response show(@PathParam("userId") String userId) {
		if(userId.equals("10"))
			return Response.ok("found it!").build();
		return Response.status(Response.Status.NOT_FOUND).build();
	}


	@POST
	public String create() {
		return "created user";
	}



	@PATCH
	@Path("{userId: [0-9]+}")
	public Response update() {
		return Response.noContent().build();
	}
}
