package http.controllers;

import annotations_.http.Authenticated;
import annotations_.http.PATCH;
import app.App;
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
public class SupplierController {

	@GET
	public Response index() {
		try (Session session = App.factory.openSession()) {
			return Response.ok(
				SupplierMapper.INSTANCE.SuppliersToSupplierViews(
					session
						.createQuery(
								"FROM Suppliers"
						).list()
				)
			).build();
		}
	}

	@GET
	@Path("{supplierId: \\d+}")
	public Response show(@PathParam("supplierId") String id) {
		try (Session session = App.factory.openSession()){
			Supplier supplier = Controller.getVerfiedItem(Supplier.class, id);
			return Response.ok(
					SupplierMapper.INSTANCE.SupplierToSupplierView(supplier)
			).build();
		}
	}

	@POST
	public Response create(@Context Guard guard, SupplierSchema schema) {
		try (Session session = App.factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Supplier supplier = SupplierMapper.INSTANCE.SupplierSchemaToSupplier(schema);
			supplier.setCreatedBy((User)guard.getUser());
			session.persist(session);
			transaction.commit();
			return Response.ok().build();
		}
	}

	@PATCH
	@Path("{supplierId: \\d+}")
	public Response update(@Context Guard guard, @PathParam("supplierId") String id, SupplierSchema schema) {
		try (Session session = App.factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Supplier supplier = Controller.getVerfiedItem(Supplier.class, id);
			supplier.setUpdatedBy((User)guard.getUser());
			SupplierUpdater.INSTANCE.updateSupplierFromSupplierSchema(
					schema,
					supplier
			);
			session.persist(supplier);
			transaction.commit();
			return Response.ok().build();
		}
	}

	@DELETE
	@Path("{supplierId: \\d+}")
	public Response delete(@Context Guard guard, @PathParam("supplierId") String id) {
		return Controller.delete(Supplier.class, id);
	}
}
