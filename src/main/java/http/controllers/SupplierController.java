package http.controllers;

import annotations.http.Authenticated;
import annotations.http.PATCH;
import app.App;
import lombok.Getter;
import models.mappers.SupplierMapper;
import models.api.schemas.SupplierSchema;
import models.db.Supplier;
import models.db.User;
import models.mappers.SupplierUpdater;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.authentication.Guard;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("suppliers")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"Pharmaceud"})
public class SupplierController implements Controller {

	@Context @Getter
	public Guard guard;

	@GET
	public Response index() {
		return this.collection(SupplierMapper.INSTANCE::SuppliersToSupplierViews, Supplier.class);
	}

	@GET
	@Path("{supplierId: \\d+}")
	public Response show(@PathParam("supplierId") String id) {
		return this.item(SupplierMapper.INSTANCE::SupplierToSupplierView, Supplier.class, id);
	}

	@POST
	public Response create(SupplierSchema schema) {
		return this.create(SupplierMapper.INSTANCE::SupplierSchemaToSupplier, schema);
	}

	@PATCH
	@Path("{supplierId: \\d+}")
	public Response update(@PathParam("supplierId") String id, SupplierSchema schema) {
		return this.update(SupplierUpdater.INSTANCE::updateSupplierFromSupplierSchema, Supplier.class, schema, id);
	}

	@DELETE
	@Path("{supplierId: \\d+}")
	public Response delete(@PathParam("supplierId") String id) {
		return this.delete(Supplier.class, id);
	}
}
