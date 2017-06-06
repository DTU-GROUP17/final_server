package http.controllers.users;


import annotations_.http.PATCH;
import app.App;
import http.requests.CreateUserInfo;
import models.api.schemas.UserSchema;
import models.api.views.UserView;
import models.db.User;
import models.mappers.UserMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("users")
//@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RolesAllowed({"Admin"})
public class UserController {

	@GET
	public Response index() {
		try (Session session = App.factory.openSession()) {

			List<User> users =
				session
					.createQuery("from User")
						.list();
			return Response.ok(users).build();
		} catch (Exception e) {
			System.out.println("e = " + e);
			return Response.notModified().build();
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
		}catch (Exception e) {
			System.out.println("e = " + e);
			return Response.notModified().build();
		}
	}

	@POST
	public Response create(UserSchema schema) {
		try (Session session = App.factory.openSession()) {
			System.out.println("create user");
			Transaction transaction = session.beginTransaction();
			User user = UserMapper.INSTANCE.UserSchemaToUser(schema);
			session.persist(user);
			transaction.commit();
			return Response.ok().build();
		} catch (Exception e) {
			System.out.println("e = " + e);
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
			if (info.getUsername()!=null)
				user.setUsername(info.getUsername());
			if (info.getPassword()!=null)
				user.setPassword(info.getPassword());
//			if (info.getRoles()!=null){
//				user.setRoles(Role.getRolesFomNames(session, info.getRoles()));
//			}
			session.persist(user);
			transaction.commit();
			return Response.ok().build();
		} catch (PersistenceException e) {
			return Response.notModified().build();
		}
	}

	@DELETE
	@Path("{userId: \\d+}")
	public Response delete(@PathParam("userId") String userId, CreateUserInfo info) {
		try (Session session = App.factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			User user = session.find(User.class, Integer.parseInt(userId));
			session.delete(user);
			transaction.commit();
			return Response.ok().build();
		} catch (PersistenceException e) {
			return Response.notModified().build();
		}
	}


}
