package http.controllers;

import annotations.http.Authenticated;
import app.App;
import lombok.Getter;
import models.api.schemas.ProductBatchSchema;
import models.db.ProductBatch;
import models.mappers.ProductBatchMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.authentication.Guard;
import services.response.ApiResponse;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("batches")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"Foreman"})
public class BatchController implements Controller {

	@Context
	@Getter
	public Guard guard;

	@GET
	public Response index() {
		return this.collection(ProductBatchMapper.INSTANCE::ProductBatchesToProductBatchViews, ProductBatch.class);
	}

	@GET
	@Path("{batchId: \\d+}")
	public Response show(@PathParam("batchId") String id) {
		return this.item(ProductBatchMapper.INSTANCE::ProductBatchToProductBatchView, ProductBatch.class, id);
	}

	@POST
	public Response create(ProductBatchSchema schema) {
		return this.create(ProductBatchMapper.INSTANCE::ProductBatchSchemaToProductBatch, schema);
	}

}
