package http.controllers.auth;


import exceptions.auth.JWTException;
import http.requests.LoginInfo;
import models.User;
import services.authentication.*;

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

		Guard guard = new JwtGuard(User.class);

		try {
			if(!guard.validate(info.getUserName(), info.getPassword())) {
				return Response.status(Status.UNAUTHORIZED).entity("Invalid credentials").build();
			}
		} catch (JWTException exception) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
		}

		//TODO: Generate JWT token and return it.
		return Response.status(200).entity("token here!").build();
	}

}
