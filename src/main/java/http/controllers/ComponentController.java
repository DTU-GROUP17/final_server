package http.controllers;

import annotations.http.Authenticated;
import lombok.Getter;
import models.api.schemas.ComponentSchema;
import models.db.Component;
import models.mappers.ComponentMapper;
import services.authentication.Guard;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("component")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"Pharmaceud"})
public class ComponentController implements Controller {

	@Context
	@Getter
	public Guard guard;

	@GET
	public Response index() {
		return this.collection(ComponentMapper.INSTANCE::ComponentsToComponentViews, Component.class);
	}

	@GET
	@Path("{componentId: \\d+}")
	public Response show(@PathParam("componentId") String id) {
		return this.item(ComponentMapper.INSTANCE::ComponentToComponentView, Component.class, id);
	}

	@POST
	public Response create(ComponentSchema schema) {
		return this.create(ComponentMapper.INSTANCE::ComponentSchemaToComponent, schema);
	}

	@DELETE
	@Path("{componentId: \\d+}")
	public Response delete(@PathParam("componentId") String id) {
		return this.delete(Component.class, id);
	}
}
