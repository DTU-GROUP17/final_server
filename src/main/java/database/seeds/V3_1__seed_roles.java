package database.seeds;

import database.HibernateMigration;
import models.db.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class V3_1__seed_roles extends HibernateMigration {

	private void createRole(Session session, String name) {
		Role admin = new Role();
		admin.setName(name);
		session.persist(admin);
	}

	@Override
	protected void migrate(Session session) throws Exception {
		Transaction transaction = session.beginTransaction();
		this.createRole(session, "Admin");
		this.createRole(session, "Pharmaceud");
		this.createRole(session, "Foreman");
		this.createRole(session, "Lab technician");
		transaction.commit();
	}
}
