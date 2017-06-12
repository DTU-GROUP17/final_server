package http.middleware;

import javax.annotation.Priority;
import javax.json.Json;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(Priorities.HEADER_DECORATOR)
public class ContentType implements ContainerResponseFilter {
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		if(!responseContext.getHeaders().containsKey("Content-Type")) {
			responseContext.getHeaders().add("Content-Type", MediaType.APPLICATION_JSON);
		}

		if (responseContext.getEntity() == null) {
			responseContext.setEntity(Json.createObjectBuilder().build());
		}
	}
}
