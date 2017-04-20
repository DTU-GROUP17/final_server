package http.controllers.users;


import annotations.http.PATCH;
import app.App;
import http.requests.CreateUserInfo;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import models.Role;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Path("users")
//@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

	@GET
	public Response index() {
		try (Session session = App.factory.openSession()) {

			List<User> users =
				session
					.createQuery("from User")
						.list();
			return Response.ok(users).build();
		}
	}

	@GET
	@Path("{userId: \\d+}")
	public Response show(@PathParam("userId") String userId) {
		try (Session session = App.factory.openSession()) {
			User user = session.find(User.class, Integer.parseInt(userId));
			if (user!=null) {
				return Response.ok(user).build();
			}
			return Response.status(Response.Status.NOT_FOUND).entity("No user with that id").build();
		}
	}

	@POST
	public Response create(CreateUserInfo info) {
		try (Session session = App.factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			User user = new User();

			user.setName(info.getName());
			user.setUserName(info.getUserName());
			user.setPassword(info.getPassword());

			Set<Role> roles = new HashSet<>(
				session
					.createQuery("from Role where name in (:roles)")
						.setParameterList("roles", info.getRoles())
							.list()
			);

			user.setRoles(roles);

			session.persist(user);

			transaction.commit();

			return Response.ok(user).build();
		} catch (PersistenceException e) {
			return Response.notModified().build();
		}
	}

	@PATCH
	@Path("{userId: \\d+}")
	public Response update(@PathParam("userId") String userId) {
		return Response.noContent().build();
	}
}
