package app;

import com.sun.net.httpserver.HttpServer;
import io.jsonwebtoken.impl.crypto.MacProvider;
import models.Role;
import models.User;
import org.flywaydb.core.Flyway;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.h2.tools.Server;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import services.authentication.Guard;
import providers.GuardProvider;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Key;
import java.sql.*;
import java.util.List;

@ApplicationPath("")
public class App extends ResourceConfig {
	public final static Key key = MacProvider.generateKey();
	public static SessionFactory factory;
	public final static String endpoint = "http://localhost:9998/";

	public static void main(String[] args) throws IOException, URISyntaxException, SQLException, ClassNotFoundException {

		Flyway flyway = new Flyway();
		flyway.setLocations("database/migrations", "database/seeds");
		flyway.setDataSource("jdbc:h2:mem:cdio_3;DB_CLOSE_DELAY=-1", "sa", "");
		flyway.migrate();

		initHibernate();

		// Create the server.
		ResourceConfig rc = ResourceConfig.forApplicationClass(App.class);
		URI endpoint = new URI(App.endpoint);
		HttpServer server = JdkHttpServerFactory.createHttpServer(endpoint, rc);


		System.out.println("Server running");
		System.out.println("Visit on: "+endpoint.toString());
		System.out.println("Hit return to stop...");
		System.in.read();
		System.out.println("Stopping server");
		server.stop(0);
		System.out.println("Server stopped");
	}

	public static void initHibernate() {
		try{
			factory =
				new Configuration()
					.configure()
						.addAnnotatedClass(User.class)
							.addAnnotatedClass(Role.class)
								.buildSessionFactory();
		}catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public App() {
		this.packages(true, "http/controllers");
		this.packages(true, "http/middleware");

		this.register(RolesAllowedDynamicFeature.class);

		register(new AbstractBinder(){
			@Override
			protected void configure() {
				bindFactory(GuardProvider.class)
						.to(Guard.class)
						.in(RequestScoped.class);
			}
		});
	}
}
