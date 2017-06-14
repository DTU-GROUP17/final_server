package http.controllers;

import annotations.http.Authenticated;
import lombok.Getter;
import models.db.Weighing;
import models.mappers.WeighingMapper;
import services.authentication.Guard;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("weighings")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"Lab_Technician"})
public class WeighingController implements Controller {

	@Context
	@Getter
	public Guard guard;

	@GET
	public Response index() {
		return this.collection(WeighingMapper.INSTANCE::WeighingsToWeighingViews, Weighing.class); //TODO: make it do the correct stuff...
	}
}
