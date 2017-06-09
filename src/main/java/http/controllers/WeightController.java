package http.controllers;

import annotations.http.PATCH;
import app.App;
import lombok.Getter;
import models.api.schemas.WeightSchema;
import models.db.User;
import models.db.Weight;
import models.mappers.WeightMapper;
import models.mappers.WeightUpdater;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.authentication.Guard;
import services.response.ApiResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("weights")
//@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RolesAllowed({"Admin"})
public class WeightController implements Controller {

	@Context @Getter
	public Guard guard;

	@GET
	public Response index() {
		return this.collection(WeightMapper.INSTANCE::WeightsToWeightViews, Weight.class);
	}

	@GET
	@Path("{weightId: \\d+}")
	public Response show(@PathParam("weightId") String weightId) {
		return this.item(WeightMapper.INSTANCE::WeightToWeightView, Weight.class, weightId);
	}

	@POST
	public Response create(WeightSchema schema) {
		return this.create(WeightMapper.INSTANCE::WeightSchemaToWeight, schema);
	}

	@PATCH
	@Path("{weightId: \\d+}")
	public Response update(@PathParam("weightId") String id, WeightSchema schema) {
		return this.update(WeightUpdater.INSTANCE::updateWeightFromWeightSchema, Weight.class, schema, id);
	}

	@DELETE
	@Path("{weightId: \\d+}")
	public Response delete(@PathParam("weightId") String id) {
		return this.delete(Weight.class, id);
	}
}
