package http.middleware;

import annotations.http.Authenticate;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Priority(Priorities.AUTHENTICATION)
@Authenticate
@Provider
public class Authentication implements ContainerRequestFilter {

	public void filter(ContainerRequestContext containerRequestContext) throws IOException {
		System.out.println("it works!");
	}
}
