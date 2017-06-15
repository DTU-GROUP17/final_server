package http.controllers;

import annotations.http.Authenticated;
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

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("weights")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"Admin"})
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
		Response response = this.create(WeightMapper.INSTANCE::WeightSchemaToWeight, schema);
		App.getWeightConnectionManager().refresh();
		return response;
	}

	@PATCH
	@Path("{weightId: \\d+}")
	public Response update(@PathParam("weightId") String id, WeightSchema schema) {
		Response response = this.update(WeightUpdater.INSTANCE::updateWeightFromWeightSchema, Weight.class, schema, id);
		App.getWeightConnectionManager().refresh();
		return response;
	}

	@DELETE
	@Path("{weightId: \\d+}")
	public Response delete(@PathParam("weightId") String id) {
		Response response = this.delete(Weight.class, id);
		App.getWeightConnectionManager().refresh();
		return response;
	}
}
