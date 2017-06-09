package http.controllers;

import annotations.http.Authenticated;
import app.App;
import lombok.Getter;
import models.db.Role;
import models.mappers.RoleMapper;
import org.hibernate.Session;
import services.authentication.Guard;
import services.response.ApiResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("roles")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoleController implements Controller {

	@Context
	@Getter
	public Guard guard;

	@GET
	public Response index() {
		return this.collection(RoleMapper.INSTANCE::RolesToRoleViews, Role.class);
	}

	@GET
	@Path("{roleId: \\d+}")
	public Response show(@PathParam("roleId") String id) {
		return this.item(RoleMapper.INSTANCE::RoleToRoleView, Role.class, id);
	}
}
