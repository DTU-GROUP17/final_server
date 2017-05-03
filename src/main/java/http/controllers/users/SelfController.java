package http.controllers.users;

import annotations.http.Authenticated;
import annotations.http.PATCH;
import app.App;
import http.requests.CreateUserInfo;
import models.Role;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.authentication.Guard;

import javax.persistence.PersistenceException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
	public Response update(@Context Guard guard, CreateUserInfo info) {
		try (Session session = App.factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			User user = (User) guard.getUser();
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