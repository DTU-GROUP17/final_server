package http.controllers.users;


import annotations_.http.Authenticated;
import annotations_.http.PATCH;
import app.App;
import http.controllers.Controller;
import lombok.Getter;
import models.api.schemas.UserSchema;
import models.db.User;
import models.mappers.UserMapper;
import models.mappers.UserUpdater;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.authentication.Guard;
import services.observer.Interceptor;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
//@RolesAllowed({"Admin"})
public class UserController implements Controller {

	@Context @Getter
	public Guard guard;

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
				Controller.getVerifiedItem(User.class, id)
			)
		).build();
	}

	@POST
	public Response create(@Context Guard guard, UserSchema schema) {
		try (Session session = App.factory.withOptions().interceptor(new Interceptor(guard.getUser())).openSession()) {
			Transaction transaction = session.beginTransaction();
			User user = UserMapper.INSTANCE.UserSchemaToUser(schema);
			session.persist(user);
			transaction.commit();
			return Response.ok().build();
		}
	}

	@PATCH
	@Path("{userId: \\d+}")
	public Response update(@PathParam("userId") String id, UserSchema schema) {
		try (Session session = App.factory.withOptions().interceptor(new Interceptor(guard.getUser())).openSession()) {
			Transaction transaction = session.beginTransaction();
			User user = Controller.getVerifiedItem(User.class, id, session);
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
	public Response delete(@PathParam("userId") String id) {
		return this.delete(User.class, id);
	}

}
