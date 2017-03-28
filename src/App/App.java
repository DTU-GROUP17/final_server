package App;

import com.sun.net.httpserver.HttpServer;
import controller.users.UserController;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;


@ApplicationPath("")
public class App extends ResourceConfig{

	public static void main(String[] args) throws IOException, URISyntaxException {

		ResourceConfig rc = ResourceConfig.forApplicationClass(App.class);
		URI endpoint = new URI("http://localhost:9998/");

		HttpServer server = JdkHttpServerFactory.createHttpServer(endpoint, rc);


		System.out.println("Server running");
		System.out.println("Visit on: "+endpoint.toString());
		System.out.println("Hit return to stop...");
		System.in.read();
		System.out.println("Stopping server");
		server.stop(0);
		System.out.println("Server stopped");
	}

	public App() {
		//this.registerClasses(UserController.class);
		packages(true, "controller");
	}
}
