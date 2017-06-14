package database.seeds;

import database.HibernateMigration;
import models.db.Weight;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class V3_7__seed_weights extends HibernateMigration {

	private void createWeight(Session session, String name, String uri){
		Weight weight = new Weight();
		weight.setName(name);
		weight.setUri(uri);
		session.persist(weight);
	}

	@Override
	protected void migrate(Session session) throws Exception {
		Transaction transaction = session.beginTransaction();
		this.createWeight(session, "Main Lab Weight", "169.254.2.3");
//		this.createWeight(session, "Cellar Weight", "192.168.0.3");
		transaction.commit();
	}
}
