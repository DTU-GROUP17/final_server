package app;

import app.App;
import com.sun.net.httpserver.HttpServer;
import io.restassured.RestAssured;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;

import java.net.URI;
import static app.App.initHibernate;

public class BaseTest {
	public HttpServer server;

	@Before
	public void setUpBaseTest() throws Exception {
		initHibernate();

		// Create the server.
		ResourceConfig rc = ResourceConfig.forApplicationClass(App.class);
		URI endpoint = new URI(App.endpoint);
		server = JdkHttpServerFactory.createHttpServer(endpoint, rc);

		RestAssured.baseURI = App.endpoint;
	}


	@After
	public void tearDownBaseTest() throws Exception {
		server.stop(0);
		server = null;
	}
}
