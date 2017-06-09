package http.controllers;

import annotations.http.Authenticated;
import app.App;
import lombok.Getter;
import models.api.schemas.MaterialSchema;
import models.db.Material;
import models.mappers.MaterialMapper;
import models.mappers.UserMapper;
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
		return this.collection(MaterialMapper.INSTANCE::MaterialsToMaterialViews, Material.class);
	}

	@GET
	@Path("{foremanId: \\d+}")
	public Response show(@PathParam("foremanId") String id) {
		return this.item(MaterialMapper.INSTANCE::MaterialToMaterialView, Material.class, id);
	}

	@POST
	public Response create(MaterialSchema schema) {
		return this.create(MaterialMapper.INSTANCE::MaterialSchemaToMaterial, schema);
	}
}
