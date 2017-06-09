package http.controllers;

import annotations.http.Authenticated;
import app.App;
import models.mappers.UserMapper;
import org.hibernate.Session;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("weighings")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"Lab Technician"})
public class WeighingController implements Controller {

	@GET
	public Response index() {
		try (Session session = App.factory.openSession()) {
			return Response.ok(
				UserMapper.INSTANCE.UsersToUserViews(
					session
						.createQuery(
							"FROM Weighings"
						).list()
				)
			).build();
		}
	}
}
