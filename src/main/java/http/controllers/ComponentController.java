package http.controllers;

import annotations_.http.Authenticated;
import annotations_.http.PATCH;
import app.App;
import models.api.schemas.ComponentSchema;
import models.db.Component;
import models.db.User;
import models.mappers.ComponentMapper;
import models.mappers.UserMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.listener.Interceptor;

import javax.annotation.security.RolesAllowed;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("component")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"Pharmaceud"})
public class ComponentController {

	@GET
	public Response index() {
		try (Session session = App.factory.openSession()) {
			return Response.ok(
				ComponentMapper.INSTANCE.CompoinentsToComponentViews(
					session
						.createQuery(
							"FROM Component"
						).list()
				)
			).build();
		}
	}

	@GET
	@Path("{componentId: \\d+}")
	public Response show(@PathParam("componentId") String id) {
		return Response.ok(
			ComponentMapper.INSTANCE.ComponentToComponentView(
				Controller.getVerifiedItem(Component.class, id)
			)
		).build();
	}

	@POST
	public Response create(ComponentSchema schema) {
		try (Session session = App.factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Component component = ComponentMapper.INSTANCE.ComponentSchemaToComponent(schema);
			session.persist(component);
			transaction.commit();
			return Response.ok().build();
		} catch (PersistenceException e) {
			return Response.notModified().build();
		}
	}

	@DELETE
	@Path("{componentId: \\d+}")
	public Response delete(@PathParam("componentId") String id) {
		return Controller.delete(Component.class, id);
	}
}
