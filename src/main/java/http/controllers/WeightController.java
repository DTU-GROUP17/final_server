package http.controllers;

import annotations_.http.Authenticated;
import annotations_.http.PATCH;
import app.App;
import http.requests.CreateWeightInfo;


import models.api.schemas.WeightSchema;
import models.db.Weight;
import models.mappers.UserMapper;
import models.mappers.WeightMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.annotation.security.RolesAllowed;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("weights")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"Admin"})
public class WeightController {

	@GET
	public Response index() {
		try (Session session = App.factory.openSession()) {
			Weight weight = (Weight) session.createQuery("FROM Weight").list().get(0);
			return Response.ok(UserMapper.INSTANCE.UserToBasicUserView(weight.getCreatedBy())).build();


//			return Response.ok(
//					WeightMapper.INSTANCE.WeightsToWeightViews(
//							session.createQuery("FROM Weight").list()
//					)
//			).build();
		}
	}

	@GET
	@Path("{weightId: \\d+}")
	public Response show(@PathParam("weightId") String weightId) {

		try (Session session = App.factory.openSession()) {
			Weight weight = session.find(Weight.class, Integer.parseInt(weightId));
			if (weight!=null) {
				return Response.ok(weight).build();
			}
			return Response.status(Response.Status.NOT_FOUND).entity("Weight's ID not found").build();
		}catch (Exception e) {
			System.out.println("e = " + e);
			return Response.notModified().build();
		}
	}

	@POST
	public Response create(WeightSchema schema) {
		try (Session session = App.factory.openSession()) {
			System.out.println("create weight");
			Transaction transaction = session.beginTransaction();
			Weight weight = WeightMapper.INSTANCE.WeightSchemaToWeight(schema);
			session.persist(weight);
			transaction.commit();
			return Response.ok().build();
		} catch (Exception e) {
			System.out.println("e = " + e);
			return Response.notModified().build();
		}
	}

	@PATCH
	@Path("{weightId: \\d+}")
	public Response update(@PathParam("weightId") String weightId, CreateWeightInfo info) {

		try (Session session = App.factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Weight weight = session.find(Weight.class, Integer.parseInt(weightId));
			if (info.getName()!=null)
				weight.setName(info.getName());
			if (info.getUri()!=null)
				weight.setUri(info.getUri());
			session.persist(weight);
			transaction.commit();
			return Response.ok().build();
		} catch (PersistenceException e) {
			return Response.notModified().build();
		}
	}

	@DELETE
	@Path("{weightId: \\d+}")
	public Response delete(@PathParam("weightId") String weightId) {

		try (Session session = App.factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Weight weight = session.find(Weight.class, Integer.parseInt(weightId));
			session.delete(weight);
			transaction.commit();
			return Response.ok().build();
		} catch (PersistenceException e) {
			return Response.notModified().build();
		}
	}
}
