package database;

import app.App;
import org.flywaydb.core.api.migration.jdbc.JdbcMigration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;

public abstract class HibernateMigration implements JdbcMigration{
	protected static SessionFactory factory;

	static {
		App.initConfiguration();
		factory = App.factory;
	}

	@Override
	public void migrate(Connection connection) throws Exception {
		try(Session session = factory.openSession()) {
			migrate(session);
		}
	}

	protected abstract void migrate(Session factory) throws Exception;
}
