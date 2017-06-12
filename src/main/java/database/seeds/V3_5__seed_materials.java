package database.seeds;

import database.HibernateMigration;
import models.api.schemas.MaterialSchema;
import models.db.Material;
import models.mappers.MaterialMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class V3_5__seed_materials extends HibernateMigration {

	private void createMaterial(Session session, MaterialSchema schema){
		Material material = MaterialMapper.INSTANCE.MaterialSchemaToMaterial(schema);
		session.persist(material);
	}

	@Override
	protected void migrate(Session session) throws Exception {
		Transaction transaction = session.beginTransaction();
		MaterialSchema schema = new MaterialSchema();
		schema.setComponent(1);
		schema.setStocked(10.0);
		schema.setSupplier(1);
		this.createMaterial(session, schema);
		schema.setComponent(2);
		schema.setStocked(13.0);
		schema.setSupplier(2);
		this.createMaterial(session, schema);
		transaction.commit();
	}
}
