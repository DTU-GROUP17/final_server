package http.controllers.users;

import annotations.http.Authenticated;
import annotations.http.PATCH;
import app.App;
import http.controllers.Controller;
import lombok.Getter;
import models.api.schemas.SelfSchema;
import models.db.User;
import models.mappers.UserMapper;
import models.mappers.UserUpdater;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.authentication.Guard;

import javax.persistence.PersistenceException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Authenticated
@Path("self")
@Produces(MediaType.APPLICATION_JSON)
public class SelfController implements Controller{

	@Context @Getter
	public Guard guard;

	@GET
	public Response show() {
		return this.item(UserMapper.INSTANCE::UserToUserView, User.class, this.getGuard().getUser().getId().toString());
	}

	@PATCH
	public Response update(SelfSchema schema) {
		return this.update(UserUpdater.INSTANCE::updateUserFromSelfSchema, User.class, schema, this.getGuard().getUser().getId().toString());
	}

	@DELETE
	public Response delete() {
		return this.delete(User.class, this.getGuard().getUser().getId().toString());
	}

}
