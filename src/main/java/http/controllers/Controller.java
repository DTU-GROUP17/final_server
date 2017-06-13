package http.controllers;

import app.App;
import exceptions.model.ModelNotFoundException;
import models.api.schemas.Schema;
import models.api.views.View;
import models.db.Model;
import models.db.timestamps.UserSoftDeleteable;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.authentication.Guard;

import javax.json.Json;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.function.*;


public interface Controller {

	Guard getGuard();

	default <T extends Model> Response collection(Function<List<T>, List<? extends View>> function, Class<T> modelClass) {
		try (Session session = App.openSession(getGuard())) {
			return Response.ok(
				function.apply(session.createQuery("FROM " + modelClass.getSimpleName()).list())
			).build();
		}
	}

	default <T extends Model> Response item(Function<T, View> function, Class<T> modelClass, String id) {
		return Response.ok(
			function.apply(
				this.getVerifiedItem(modelClass, id)
			)
		).build();
	}

	default <T extends Schema> Response create(Function<T, Model> function, T schema) {
		try (Session session = App.openSession(this.getGuard())) {
			Transaction transaction = session.beginTransaction();
			Model model = function.apply(schema);
			session.persist(model);
			transaction.commit();
			return Response.status(Response.Status.CREATED)
				.entity(
					Json.createObjectBuilder()
						.add("id", model.getId())
				).build();
		}
	}

	default <T extends Schema, U extends Model> Response update(BiConsumer<T, U> consumer, Class<U> modelClass, T schema, String id) {
		try (Session session = App.openSession(this.getGuard())) {
			Transaction transaction = session.beginTransaction();
			U model = this.getVerifiedItem(modelClass, id);
			consumer.accept(schema, model);
			session.update(model);
			transaction.commit();
			return Response.ok().build();
		}
	}


	static <T extends Model> T getVerifiedItem(Class<T> klass, String id, Session session) {
		T item = session.find(klass, Integer.parseInt(id));
		if (item == null) {
			throw new ModelNotFoundException(klass.getSimpleName());
		}
		return item;
	}

	default <T extends Model> T getVerifiedItem(Class<T> klass, String id) {
		try (Session session = App.openSession(this.getGuard())) {
			return Controller.getVerifiedItem(klass, id, session);
		}
	}

	static <T extends Model & UserSoftDeleteable> Response delete(Class<T> klass, String id, Session session) {
		Transaction transaction = session.beginTransaction();
		session.delete(
			Controller.getVerifiedItem(klass, id, session)
		);
		transaction.commit();
		return Response.ok().build();

	}

	default <T extends Model & UserSoftDeleteable> Response delete(Class<T> klass, String id) {
		try (Session session = App.openSession(this.getGuard())) {
			return Controller.delete(klass, id, session);
		}
	}

}
