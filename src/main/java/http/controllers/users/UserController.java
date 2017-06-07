package http.controllers.users;


import annotations_.http.Authenticated;
import annotations_.http.PATCH;
import app.App;
import http.requests.CreateUserInfo;
import models.api.schemas.UserSchema;
import models.api.views.UserView;
import models.db.User;
import models.mappers.UserMapper;
import models.mappers.UserUpdater;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.authentication.Guard;
import services.listener.Interceptor;

import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RolesAllowed({"Admin"})
public class UserController {

	@GET
	public Response index(@DefaultValue("false")@QueryParam("withDeleted") boolean withDeleted, @Context Guard guard) {
		try (Session session = App.factory.openSession()) {
			return Response.ok(
					UserMapper.INSTANCE.UsersToUserViews(
						session
							.createQuery(
								"FROM User"
							).list()
					)
			).build();
		}
	}

	@GET
	@Path("{userId: \\d+}")
	public Response show(@PathParam("userId") String userId) {
		try (Session session = App.factory.openSession()) {
			UserView view = UserMapper.INSTANCE.UserToUserView(
				session.find(User.class, Integer.parseInt(userId))
			);
			if (view!=null) {
				return Response.ok(view).build();
			}
			return Response.status(Response.Status.NOT_FOUND).entity("No user with that id").build();
		}
	}

	@POST
	@Authenticated
	public Response create(@Context Guard guard, UserSchema schema) {
		try (Session session = App.factory.withOptions().interceptor(new Interceptor(guard.getUser())).openSession()) {
			Transaction transaction = session.beginTransaction();
			User user = UserMapper.INSTANCE.UserSchemaToUser(schema);
			user.setCreatedBy((User)guard.getUser());
			session.persist(user);
			transaction.commit();
			return Response.ok().build();
		} catch (PersistenceException e) {
			return Response.notModified().build();
		}
	}

	@PATCH
	@Path("{userId: \\d+}")
	public Response update(@Context Guard guard, @PathParam("userId") String userId, UserSchema schema) {
		try (Session session = App.factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			User user = session.find(User.class, Integer.parseInt(userId));
			if (user==null){
				return Response
					.status(Response.Status.NOT_FOUND).entity("No user with that id").build();
			}
			user.setUpdatedBy((User)guard.getUser());
			UserUpdater.INSTANCE.updateUserFromUserSchema(
				schema,
				user
			);
			session.persist(user);
			transaction.commit();
			return Response.ok().build();
		}
	}

	@DELETE
	@Path("{userId: \\d+}")
	public Response delete(@PathParam("userId") String userId, CreateUserInfo info) {
		try (Session session = App.factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			User user = session.find(User.class, Integer.parseInt(userId));
			if (user==null){
				return Response
					.status(Response.Status.NOT_FOUND).entity("No user with that id").build();
			}
			session.delete(user);
			transaction.commit();
			return Response.ok().build();
		}
	}

}
