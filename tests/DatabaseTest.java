import org.assertj.db.type.Source;
import org.flywaydb.core.Flyway;
import org.junit.Before;

public abstract class DatabaseTest {

	protected static final String databaseURI = "jdbc:mysql://localhost:3306/cdio_3?serverTimezone=UTC&nullNamePatternMatchesAll=true";
	protected static final String databaseUsername = "root";
	protected static final String databaseLogin = "";

	public Source source;

	@Before
	public void setUpBaseTest() throws Exception {

		source = new Source(databaseURI, databaseUsername, databaseLogin);

		// Clean and migrate database.
		Flyway flyway = new Flyway();
		flyway.setDataSource(databaseURI, databaseUsername, databaseLogin);
		flyway.setLocations("filesystem:src/database/migrations");
		flyway.clean();
		flyway.migrate();

		// Migrate the test data.
		flyway.setLocations("filesystem:tests/seeds");
		flyway.setValidateOnMigrate(false);
		flyway.migrate();
	}
}
