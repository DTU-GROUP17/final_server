package http.controllers.auth;


import exceptions.auth.JWTException;
import http.requests.LoginInfo;
import services.authentication.*;

import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("authentication")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthController {

	@POST
	@Path("login")
	public Response loginUser(LoginInfo info, @Context Guard guard) {
		if (info == null || info.getUserName() == null || info.getPassword() == null) {
			return Response.status(Status.UNAUTHORIZED).build();
		}

		if(!guard.validate(info.getUserName(), info.getPassword())) {
			return Response.status(Status.UNAUTHORIZED).entity("Invalid credentials").build();
		}

		return Response.status(200)
			.entity(
				Json.createObjectBuilder()
					.add("message", ((JwtGuard) guard).generateToken().getToken())
					.add("status", 200)
			).build();
	}

}
