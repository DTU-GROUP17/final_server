package app;

import io.jsonwebtoken.impl.crypto.MacProvider;
import lombok.Getter;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import providers.GuardProvider;
import services.authentication.Guard;
import services.observer.Interceptor;
import weighting.WeightConnectionManager;

import javax.ws.rs.ApplicationPath;
import java.security.Key;

@ApplicationPath("")
public class App extends ResourceConfig {
	public final static Key key = MacProvider.generateKey();
	public static SessionFactory factory;
	public static Configuration configuration;
	public final static String endpoint = "http://localhost";
	@Getter private static WeightConnectionManager weightConnectionManager;

	public static void initHibernate() {
		try{
			initConfiguration();
			factory = configuration.buildSessionFactory();

		}catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void initConfiguration() {
		configuration = new Configuration()
			.configure();
	}

	public static Session openSession(Guard guard) {
		return App.factory.withOptions().interceptor(new Interceptor(guard.getUser())).openSession();
	}

	public static Session openSession() {
		return App.factory.openSession();
	}

	public App() {
		this.packages(true, "exceptions");
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

		App.weightConnectionManager = new WeightConnectionManager();
	}
}
