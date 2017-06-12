package services.response;

import exceptions.model.ModelNotFoundException;
import models.api.views.View;
import models.db.Model;

import javax.ws.rs.core.Response;

public abstract class ApiResponse extends Response{

	public static ResponseBuilder item(View view) throws ModelNotFoundException {
		if(view != null) {
			return Response.ok(view);
		}
		throw new ModelNotFoundException("haardt");
	}

//	public static void verifyItem(Model model) throws ModelNotFoundException {
//
//	}
}
