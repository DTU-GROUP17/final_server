package app;

import com.sun.net.httpserver.HttpServer;
import io.restassured.RestAssured;
import org.flywaydb.core.Flyway;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;

import java.net.URI;

import static app.App.initConfiguration;

public class BaseTest {
	public HttpServer server;

	@Before
	public void setUpBaseTest() throws Exception {
		// Migrate database
		Flyway flyway = new Flyway();
		flyway.setLocations("database.migrations", "database.seeds");
		flyway.setDataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
		flyway.migrate();


		// Setup hibernate
		initConfiguration();
		App.configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		App.configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
		App.configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
		App.configuration.setProperty("hibernate.connection.username", "sa");
		App.factory = App.configuration.buildSessionFactory();


		// Create the server.
		ResourceConfig rc = ResourceConfig.forApplicationClass(App.class);
		URI endpoint = new URI(App.endpoint+":9999/");
		server = JdkHttpServerFactory.createHttpServer(endpoint, rc);

		RestAssured.baseURI = App.endpoint+":9999/";
	}


	@After
	public void tearDownBaseTest() throws Exception {
		server.stop(0);
		server = null;
	}
}
