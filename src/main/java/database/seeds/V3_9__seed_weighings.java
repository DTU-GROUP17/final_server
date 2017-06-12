package database.seeds;

import database.HibernateMigration;
import models.db.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Timestamp;

public class V3_9__seed_weighings extends HibernateMigration {

	private void createWeighing(Session session, Integer productBatch, Integer user, Integer weight, Integer material, Double amount){
		Weighing weighing = new Weighing();
		ProductBatch forBatch = new ProductBatch();
		forBatch.setId(productBatch);
		weighing.setProductBatch(forBatch);
		User weighedBy = new User();
		weighedBy.setId(user);
		weighing.setWeighedBy(weighedBy);
		Weight usedWeight = new Weight();
		usedWeight.setId(weight);
		weighing.setWeight(usedWeight);
		Material usedMaterial = new Material();
		usedMaterial.setId(material);
		weighing.setMaterial(usedMaterial);
		weighing.setAmount(amount);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		weighing.setWeighedAt(timestamp);
		session.persist(weighing);
	}

	@Override
	protected void migrate(Session session) throws Exception {
		Transaction transaction = session.beginTransaction();
		this.createWeighing(session, 1, 1, 1, 1, 10.2);
		this.createWeighing(session, 1, 1, 1, 2, 2.6);
		transaction.commit();
	}
}
