package http.controllers;

import annotations.http.Authenticated;
import app.App;
import lombok.Getter;
import models.db.Weighing;
import models.mappers.UserMapper;
import models.mappers.WeighingMapper;
import org.hibernate.Session;
import services.authentication.Guard;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("weighings")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"Lab Technician"})
public class WeighingController implements Controller {

	@Context
	@Getter
	public Guard guard;

	@GET
	public Response index() {
		return this.collection(WeighingMapper.INSTANCE::WeighingsToWeighingViews, Weighing.class); //TODO: make it do the correct stuff...
	}
}
