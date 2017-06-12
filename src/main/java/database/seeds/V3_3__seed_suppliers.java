package database.seeds;

import database.HibernateMigration;
import models.db.Supplier;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class V3_3__seed_suppliers extends HibernateMigration {

	private void createSupplier(Session session, String name) {
		Supplier supplier = new Supplier();
		supplier.setName(name);
		session.persist(supplier);
	}

	@Override
	protected void migrate(Session session) throws Exception {
		Transaction transaction = session.beginTransaction();
		this.createSupplier(session, "Hansens Æbler");
		this.createSupplier(session, "Bents Mølle");
		transaction.commit();
	}
}
