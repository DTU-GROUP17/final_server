package http.controllers.auth;


import http.requests.LoginInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthController {

	@POST
	@Path("login")
	public Response loginUser(LoginInfo info) {
		if (info == null) {
			Response.status(403).build();
		}

		System.out.println("works");

		return Response.ok("works").build();
	}

}
