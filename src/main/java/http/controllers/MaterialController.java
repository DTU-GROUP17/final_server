package http.controllers;

import annotations.http.Authenticated;
import app.App;
import lombok.Getter;
import models.api.schemas.MaterialSchema;
import models.db.Material;
import models.mappers.MaterialMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.authentication.Guard;

import javax.annotation.security.RolesAllowed;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("materials")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"Foreman"})
public class MaterialController implements Controller {

	@Context
	@Getter
	public Guard guard;

	@GET
	public Response index() {
		try (Session session = App.factory.openSession()) {
			return Response.ok(
				MaterialMapper.INSTANCE.MaterialsToMaterialViews(
					session
						.createQuery(
							"FROM Material"
						).list()
				)
			).build();
		}
	}

	@GET
	@Path("{foremanId: \\d+}")
	public Response show(@PathParam("foremanId") String id) {
		return Response.ok(
			MaterialMapper.INSTANCE.MaterialToMaterialView(
				this.getVerifiedItem(Material.class, id)
			)
		).build();
	}

	@POST
	public Response create(MaterialSchema schema) {
		try (Session session = App.factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.persist(
				MaterialMapper.INSTANCE.MaterialSchemaToMaterial(schema)
			);
			transaction.commit();
			return Response.ok().build();
		} catch (PersistenceException e) {
			return Response.notModified().build();
		}
	}
}
