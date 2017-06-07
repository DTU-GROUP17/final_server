package http.controllers;

import annotations_.http.Authenticated;
import annotations_.http.PATCH;
import app.App;
import http.requests.CreateWeightInfo;
import models.api.schemas.WeightSchema;
import models.db.User;
import models.db.Weight;
import models.mappers.UserMapper;
import models.mappers.WeightMapper;
import models.mappers.WeightUpdater;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.authentication.Guard;

import javax.annotation.security.RolesAllowed;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("weights")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"Admin"})
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
			Weight weight = session.find(Weight.class, Integer.parseInt(weightId));
			if (weight!=null) {
				return Response.ok(WeightMapper.INSTANCE.WeightToWeightView(weight)).build();
			}
			return Response.status(Response.Status.NOT_FOUND).entity("No weight with that id").build();
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
	public Response update(@Context Guard guard, @PathParam("weightId") String weightId, WeightSchema schema) {
		try (Session session = App.factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Weight weight = session.find(Weight.class, Integer.parseInt(weightId));
			if (weight==null){
				return Response
					.status(Response.Status.NOT_FOUND).entity("No weight with that id").build();
			}
			weight.setUpdatedBy((User)guard.getUser());
			WeightUpdater.INSTANCE.updateWeightFromWeightSchema(schema, weight);
			session.persist(weight);
			transaction.commit();
			return Response.ok().build();
		} catch (PersistenceException e) {
			return Response.notModified().build();
		}
	}

	@DELETE
	@Path("{weightId: \\d+}")
	public Response delete(@Context Guard guard, @PathParam("weightId") String weightId) {
		try (Session session = App.factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Weight weight = session.find(Weight.class, Integer.parseInt(weightId));
			if (weight==null){
				return Response
					.status(Response.Status.NOT_FOUND).entity("No weight with that id").build();
			}
			weight.setDeletedBy((User)guard.getUser());
			session.delete(weight);
			transaction.commit();
			return Response.ok().build();
		} catch (PersistenceException e) {
			return Response.notModified().build();
		}
	}
}
