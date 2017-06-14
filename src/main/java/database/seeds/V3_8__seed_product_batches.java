package database.seeds;

import database.HibernateMigration;
import models.api.schemas.ProductBatchSchema;
import models.db.ProductBatch;
import models.mappers.ProductBatchMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class V3_8__seed_product_batches extends HibernateMigration {

	private void createBatch(Session session, Integer recipe){
		ProductBatchSchema schema = new ProductBatchSchema();
		schema.setRecipe(recipe);
		ProductBatch batch = ProductBatchMapper.INSTANCE.ProductBatchSchemaToProductBatch(schema);
		session.persist(batch);
	}

	@Override
	protected void migrate(Session session) throws Exception {
		Transaction transaction = session.beginTransaction();
		this.createBatch(session, 1);
		this.createBatch(session, 1);
		transaction.commit();
	}
}
