package http.controllers;

import annotations_.http.PATCH;
import app.App;
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
public class WeightController {

	@GET
	public Response index() {
		try (Session session = App.factory.openSession()) {
			return Response.ok(
				WeightMapper.INSTANCE.WeightsToWeightViews(
					session.createQuery("FROM Weight").list()
				)
			).build();
		}
	}

	@GET
	@Path("{weightId: \\d+}")
	public Response show(@PathParam("weightId") String weightId) {
		try (Session session = App.factory.openSession()) {
			return ApiResponse.item(
				WeightMapper.INSTANCE.WeightToWeightView(
					session.find(Weight.class, Integer.parseInt(weightId))
				)
			).build();
		}
	}

	@POST
	public Response create(@Context Guard guard, WeightSchema schema) {
		try (Session session = App.factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Weight weight = WeightMapper.INSTANCE.WeightSchemaToWeight(schema);
			weight.setCreatedBy((User)guard.getUser());
			session.persist(weight);
			transaction.commit();
			return Response.ok().build();
		}
	}

	@PATCH
	@Path("{weightId: \\d+}")
	public Response update(@Context Guard guard, @PathParam("weightId") String id, WeightSchema schema) {
		try (Session session = App.factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Weight weight = Controller.getVerifiedItem(Weight.class, id);
			weight.setUpdatedBy((User)guard.getUser());
			WeightUpdater.INSTANCE.updateWeightFromWeightSchema(schema, weight);
			session.persist(weight);
			transaction.commit();
			return Response.ok().build();
		}
	}

	@DELETE
	@Path("{weightId: \\d+}")
	public Response delete(@Context Guard guard, @PathParam("weightId") String id) {
		return null;
		//return Controller.delete(Weight.class, id);
	}
}
