package http.controllers;

import annotations.http.Authenticated;
import app.App;
import models.api.schemas.ProductBatchSchema;
import models.db.ProductBatch;
import models.mappers.ProductBatchMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.response.ApiResponse;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("batches")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"Foreman"})
public class BatchController implements Controller {

	@GET
	public Response index() {
		try (Session session = App.factory.openSession()) {
			return Response.ok(
				ProductBatchMapper.INSTANCE.ProductBatchesToProductBatchViews(
					session
						.createQuery(
							"FROM ProductBatch"
						).list()
				)
			).build();
		}
	}

	@GET
	@Path("{batchId: \\d+}")
	public Response show(@PathParam("batchId") String id) {
		try (Session session = App.factory.openSession()) {
			return ApiResponse.item(
				ProductBatchMapper.INSTANCE.ProductBatchToProductBatchView(
					session.find(ProductBatch.class, Integer.parseInt(id))
				)
			).build();
		}
	}

	@POST
	public Response create(ProductBatchSchema schema) {
		try (Session session = App.factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.persist(
				ProductBatchMapper.INSTANCE.ProductBatchSchemaToProductBatch(schema)
			);
			transaction.commit();
			return Response.ok().build();
		}
	}

}
