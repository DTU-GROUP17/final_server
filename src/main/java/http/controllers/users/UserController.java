package http.controllers.users;


import annotations.http.Authenticated;
import annotations.http.PATCH;
import app.App;
import exceptions.CreateUserException;
import http.requests.CreateUserInfo;
import models.Role;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.annotation.security.RolesAllowed;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("users")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"admin"})
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
			if (!info.isFull())
				throw new CreateUserException();
			Transaction transaction = session.beginTransaction();
			User user = new User();

			user.setName(info.getName());
			user.setUserName(info.getUserName());
			user.setPassword(info.getPassword());

			user.setRoles(Role.getRolesFomNames(session, info.getRoles()));

			session.persist(user);

			transaction.commit();

			return Response.ok(user).build();
		} catch (PersistenceException | CreateUserException e) {
			return Response.notModified().build();
		}
	}

	@PATCH
	@Path("{userId: \\d+}")
	public Response update(@PathParam("userId") String userId, CreateUserInfo info) {
		try (Session session = App.factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			User user = session.find(User.class, Integer.parseInt(userId));
			if (info.getName()!=null)
				user.setName(info.getName());
			if (info.getUserName()!=null)
				user.setUserName(info.getUserName());
			if (info.getPassword()!=null)
				user.setPassword(info.getPassword());
			if (info.getRoles()!=null){
				user.setRoles(Role.getRolesFomNames(session, info.getRoles()));
			}
			session.persist(user);
			transaction.commit();
			return Response.ok().build();
		} catch (PersistenceException e) {
			return Response.notModified().build();
		}
	}
}
