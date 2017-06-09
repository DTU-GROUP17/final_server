package http.controllers;

import app.App;
import exceptions.model.ModelNotFoundException;
import models.db.Model;
import models.db.SoftDeletable;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.core.Response;


public interface Controller {

	static <T extends Model> T getVerifiedItem(Class<T> klass, String id, Session session){
		T item = session.find(klass, Integer.parseInt(id));
		if (item == null) {
			throw new ModelNotFoundException();
		}
		return item;
	}

	static <T extends Model> T getVerifiedItem(Class<T> klass, String id) throws ModelNotFoundException {
		try (Session session = App.factory.openSession()) {
			return Controller.getVerifiedItem(klass, id, session);
		}
	}

	static <T extends Model & SoftDeletable> Response delete(Class<T> klass, String id, Session session) {
		Transaction transaction = session.beginTransaction();
		session.delete(
			Controller.getVerifiedItem(klass, id, session)
		);
		transaction.commit();
		return Response.ok().build();

	}

	static <T extends Model & SoftDeletable> Response delete(Class<T> klass, String id) {
		try (Session session = App.factory.openSession()) {
			return Controller.delete(klass, id, session);
		}
	}

}
