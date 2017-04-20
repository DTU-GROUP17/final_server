package http.controllers.users;


import annotations.http.PATCH;
import app.App;
import http.requests.CreateUserInfo;
import models.Role;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;

@Path("users")
//@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

	@GET
	public String index() {
		return "all users";
	}

	@GET
	@Path("{userId: [0-9]+}")
	public Response show(@PathParam("userId") String userId) {
//		User user = new User();
//		Role role1 = new Role();
//		role1.setName("sej");
//		Role role2 = new Role();
//		role2.setName("type");
//		Set<Role> roles = new HashSet<>();
//		roles.add(role1);
//		roles.add(role2);
//		user.setPassword("hemmeligt");
//		user.setName("Hans");
//		user.setRoles(roles);
		Session session = App.factory.openSession();
		System.out.println("userId = " + userId);
		int uid = Integer.parseInt(userId);
		System.out.println("uid = " + uid);
		User user = new User();
		try {
			user = session.get(User.class, 2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("user = " + user);
		return Response.ok(user).build();
//		if(userId.equals("10"))
//			return Response.ok("found it!").build();
//		return Response.status(Response.Status.NOT_FOUND).build();
	}


	@POST
	public Response create(CreateUserInfo info) {
		try (Session session = App.factory.openSession()) {
			System.out.println(info.getName());
			System.out.println(info.getPassword());
			Transaction transaction = session.beginTransaction();
			User user = new User();
			user.setName(info.getName());
			user.setPassword(info.getPassword());

			session.save(user);

			transaction.commit();

			return Response.ok(user).build();
		}
	}



	@PATCH
	@Path("{userId: [0-9]+}")
	public Response update() {
		return Response.noContent().build();
	}
}
