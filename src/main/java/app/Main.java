package app;

import com.sun.net.httpserver.HttpServer;
import org.flywaydb.core.Flyway;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.Hibernate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Main {
	public static String port = "8080";

	public static void main(String[] args) throws IOException, URISyntaxException {
		App.initHibernate();

		Flyway flyway = new Flyway();
		flyway.setLocations("database.migrations", "database.seeds");
		flyway.setDataSource("jdbc:h2:mem:cdio_3;DB_CLOSE_DELAY=-1", "sa", "");
		flyway.migrate();

		for (String arg : args) {
			if(arg.startsWith("port=")) {
				port = arg.substring(5);
			}
		}
		// Create the server.
		ResourceConfig rc = ResourceConfig.forApplicationClass(App.class);
		URI endpoint = new URI(App.endpoint+":"+port+"/");
		HttpServer server = JdkHttpServerFactory.createHttpServer(endpoint, rc);


		System.out.println("Server running");
		System.out.println("Visit on: "+endpoint.toString());
		System.out.println("Hit return to stop...");
		System.in.read();
		System.out.println("Stopping server");
		server.stop(0);
		System.out.println("Server stopped");

		System.exit(0);
	}
}
