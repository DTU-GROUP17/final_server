package database.seeds;

import database.HibernateMigration;
import models.db.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class V3_1__CE_lugter extends HibernateMigration {

	@Override
	protected void migrate(Session session) throws Exception {
		Transaction transaction = session.beginTransaction();
		User user = new User();
		user.setName("test");
		user.setUsername("oooh nnooo");
		user.setPassword("crap");
		session.persist(user);
		transaction.commit();
	}
}
