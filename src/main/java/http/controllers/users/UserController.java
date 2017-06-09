package http.controllers.users;


import annotations.http.Authenticated;
import annotations.http.PATCH;
import http.controllers.Controller;
import lombok.Getter;
import models.api.schemas.UserSchema;
import models.db.User;
import models.mappers.UserMapper;
import models.mappers.UserUpdater;
import services.authentication.Guard;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
//@RolesAllowed({"Admin"})
public class UserController implements Controller {

	@Context @Getter
	public Guard guard;

	@GET
	public Response index(@DefaultValue("false")@QueryParam("withDeleted") boolean withDeleted) {
		return this.collection(UserMapper.INSTANCE::UsersToUserViews, User.class);
	}

	@GET
	@Path("{userId: \\d+}")
	public Response show(@PathParam("userId") String id) {
		return this.item(UserMapper.INSTANCE::UserToUserView, User.class, id);
	}

	@POST
	public Response create(UserSchema schema) {
		return this.create(UserMapper.INSTANCE::UserSchemaToUser, schema);
	}

	@PATCH
	@Path("{userId: \\d+}")
	public Response update(@PathParam("userId") String id, UserSchema schema) {
		return this.update(UserUpdater.INSTANCE::updateUserFromUserSchema, User.class, schema, id);
	}

	@DELETE
	@Path("{userId: \\d+}")
	public Response delete(@PathParam("userId") String id) {
		return this.delete(User.class, id);
	}

}
