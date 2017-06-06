package http.controllers.users;

import annotations_.http.Authenticated;
import annotations_.http.PATCH;
import app.App;
import models.db.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.authentication.Guard;

import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Authenticated
@Path("self")
@Produces(MediaType.APPLICATION_JSON)
public class SelfController {

	@GET
	public Response show(@Context Guard guard) {
		return Response.ok(guard.getUser()).build();
	}

	@PATCH
	public Response update(@Context Guard guard, User info) {
		try (Session session = App.factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			User user = (User) guard.getUser();

			if (info.getName()!=null)
				user.setName(info.getName());
			if (info.getUsername()!=null)
				user.setUsername(info.getUsername());
			if (info.getPassword()!=null)
				user.setPassword(info.getPassword());
			
			session.saveOrUpdate(user);
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
