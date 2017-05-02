package http.middleware;

import javax.annotation.Priority;
import javax.json.*;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(Priorities.ENTITY_CODER)
public class JsonBuild implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		if(responseContext.getEntity() instanceof JsonArrayBuilder) {
			responseContext.setEntity(
					((JsonArrayBuilder) responseContext.getEntity()).build().toString()
			);
		}
		else if(responseContext.getEntity() instanceof JsonObjectBuilder) {
			responseContext.setEntity(
					((JsonObjectBuilder) responseContext.getEntity()).build().toString()
			);
		}
	}
}
