package http.controllers.auth;


import exceptions.auth.JWTException;
import http.requests.LoginInfo;
import models.User;
import services.authentication.*;

import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("authentication")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthController {

	@POST
	@Path("login")
	public Response loginUser(LoginInfo info) {
		if (info == null) {
			Response.status(Status.FORBIDDEN).build();
		}
		assert info != null;

		JwtGuard guard = new JwtGuard(User.class);

		try {
			if(!guard.validate(info.getUserName(), info.getPassword())) {
				return Response.status(Status.UNAUTHORIZED).entity("Invalid credentials").build();
			}
		} catch (JWTException exception) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
		}

		return Response.status(200)
			.entity(
				Json.createObjectBuilder()
					.add("message", guard.generateToken().getToken())
					.add("status", 200)
			).build();
	}

}
