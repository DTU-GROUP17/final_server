package database.seeds;

import database.HibernateMigration;
import models.db.Component;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class V3_4__seed_components extends HibernateMigration {

	void createComponent(Session session, String name){
		Component component = new Component();
		component.setName(name);
		session.persist(component);
	}

	@Override
	protected void migrate(Session session) throws Exception {
		Transaction transaction = session.beginTransaction();
		this.createComponent(session, "Tomat");
		this.createComponent(session, "Mel");
		transaction.commit();
	}
}
