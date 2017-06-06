package app;

import com.sun.net.httpserver.HttpServer;
import models.api.schemas.UserSchema;
import models.api.views.RoleView;
import models.api.views.UserView;
import models.db.Role;
import models.db.User;
import models.mappers.RoleMapper;
import models.mappers.UserMapper;
import org.flywaydb.core.Flyway;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

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

//		AndenRIP andenRIP = new AndenRIP();
//		andenRIP.setNumber(10);
//
//		RIP rip = RIPMapper.INSTANCE.AndenRIPToRIP(andenRIP);
//
//		AndenRIP helevejen = RIPMapper.INSTANCE.RIPToAndenRIP(rip);
//
//		System.out.println("rip = " + rip.getValue());
//
//		System.out.println("helevejen = " + helevejen.getNumber());

		Role role = new Role();

		role.setName("coolguy");

		Set<Role> roles = new HashSet<>();

		roles.add(role);

		UserSchema schema = new UserSchema();

		schema.setName("hans");
		schema.setUsername("haard");
		schema.setPassword("wauw");

		System.out.println("schema = " + schema);

		User user = UserMapper.INSTANCE.UserSchemaToUser(schema);

		user.setRoles(roles);

		System.out.println("user = " + user);

		UserView view = UserMapper.INSTANCE.UserToUserView(user);

		System.out.println("view = " + view);

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
