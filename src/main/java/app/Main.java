package app;

import com.sun.net.httpserver.HttpServer;
import models.api.schemas.UserSchema;
import models.api.views.RoleView;
import models.api.views.UserView;
import models.db.*;
import models.mappers.RoleMapper;
import models.mappers.UserMapper;
import org.flywaydb.core.Flyway;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.Session;
import weighting.WeightConnectionManager;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static String port = "9998";

	public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException {
		Class.forName("org.h2.Driver");

		App.initHibernate();

		Flyway flyway = new Flyway();
		flyway.setLocations("database.migrations", "database.seeds");
		flyway.setDataSource("jdbc:h2:mem:final;DB_CLOSE_DELAY=-1", "", "");
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

		App.getWeightConnectionManager().refresh();

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
