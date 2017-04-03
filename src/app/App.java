package app;

import com.sun.net.httpserver.HttpServer;
import io.jsonwebtoken.impl.crypto.MacProvider;
import models.User;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Key;


@ApplicationPath("")
public class App extends ResourceConfig{
	public final static Key key = MacProvider.generateKey();

	public static void main(String[] args) throws IOException, URISyntaxException {
		// Create the server.
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
		this.packages(true, "http/controllers");
		this.packages(true, "http/middleware");
	}
}
