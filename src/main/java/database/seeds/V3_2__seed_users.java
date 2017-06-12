package database.seeds;

import database.HibernateMigration;
import models.db.Role;
import models.db.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class V3_2__seed_users extends HibernateMigration {

	private void createUser(Session session, String name, String username, String password, Set<Role> roles) {
		User user = new User();
		user.setName(name);
		user.setUsername(username);
		user.setPassword(password);
		user.setRoles(roles);
		session.persist(user);
	}

	@Override
	protected void migrate(Session session) throws Exception {
		Transaction transaction = session.beginTransaction();
		Set<Role> roles = new HashSet<>();
		Role role = session.find(Role.class, 1);
		roles.add(role);

		Set<Role> allRoles = new HashSet<>(session.createQuery("FROM Role ").list());

		this.createUser(session, "hax", "hax", "frækfyr69", allRoles);
		this.createUser(session, "Hans", "rekker", "hemmeligt", roles);
		this.createUser(session, "Lone", ":)", "frækfyr69", roles);
		this.createUser(session, "Linda", ":(", "albert.dk", roles);
		transaction.commit();
	}
}
