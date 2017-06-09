package http.controllers.users;

import annotations.http.Authenticated;
import annotations.http.PATCH;
import app.App;
import http.controllers.Controller;
import models.api.schemas.SelfSchema;
import models.db.User;
import models.mappers.UserMapper;
import models.mappers.UserUpdater;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.authentication.Guard;

import javax.persistence.PersistenceException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Authenticated
@Path("self")
@Produces(MediaType.APPLICATION_JSON)
public class SelfController implements Controller{

	@GET
	public Response show(@Context Guard guard) {
		return Response.ok(UserMapper.INSTANCE.UserToUserView((User) guard.getUser())).build();
	}

	@PATCH
	public Response update(@Context Guard guard, SelfSchema schema) {
		try (Session session = App.factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			User user = (User) guard.getUser();
			UserUpdater.INSTANCE.updateUserFromSelfSchema(schema, user);
			transaction.commit();
			return Response.ok().build();
		} catch (PersistenceException e) {
			return Response.notModified().build();
		}
	}

	@DELETE
	public Response delete(@Context Guard guard) {
		try (Session session = App.factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			User user = (User) guard.getUser();
			session.delete(user);
			transaction.commit();
			return Response.ok().build();
		} catch (PersistenceException e) {
			return Response.notModified().build();
		}
	}

}
