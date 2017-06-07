package http.controllers.users;

import annotations_.http.PATCH;
import app.App;
import http.controllers.Controller;
import models.api.schemas.UserSchema;
import models.db.User;
import models.mappers.UserMapper;
import models.mappers.UserUpdater;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.authentication.Guard;

import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
//@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RolesAllowed({"Admin"})
public class UserController implements Controller {

	@GET
	public Response index(@DefaultValue("false")@QueryParam("withDeleted") boolean withDeleted) {
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
	public Response show(@PathParam("userId") String id) {
		return Response.ok(
			UserMapper.INSTANCE.UserToUserView(
				Controller.getVerfiedItem(User.class, id)
			)
		).build();
	}

	@POST
	public Response create(@Context Guard guard, UserSchema schema) {
		try (Session session = App.factory.openSession()) {
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
	public Response update(@Context Guard guard, @PathParam("userId") String id, UserSchema schema) {
		try (Session session = App.factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			User user = Controller.getVerfiedItem(User.class, id, session);
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
	public Response delete(@Context Guard guard, @PathParam("userId") String id) {
		return Controller.delete(User.class, id);
	}

}
