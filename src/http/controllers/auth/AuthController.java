package http.controllers.auth;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthController {

	@GET
	@Path("login")
	public Response authenticateUser() {
		return Response.ok("works").build();
	}
}
