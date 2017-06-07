package http.controllers;

import app.App;
import exceptions.DeleteUserException;
import exceptions.model.ModelNotFoundException;
import models.db.Model;
import models.db.SoftDeletable;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.core.Response;


public interface Controller {

	static <T extends Model> T getVerfiedItem(Class<T> klass, String id, Session session){
		T item = session.find(klass, Integer.parseInt(id));
		if (item == null) {
			throw new ModelNotFoundException();
		}
		return item;
	}

	static <T extends Model> T getVerfiedItem(Class<T> klass, String id) throws ModelNotFoundException {
		try (Session session = App.factory.openSession()) {
			return Controller.getVerfiedItem(klass, id, session);
		}
	}

	static <T extends Model & SoftDeletable> Response delete(Class<T> klass, String id) throws DeleteUserException {
		try (Session session = App.factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			T item;
			try {
				item = klass.newInstance();
			} catch (IllegalAccessException | InstantiationException e){
				throw new DeleteUserException();
			}
			item.setId(Integer.parseInt(id));
			session.delete(item);
			transaction.commit();
			return Response.ok().build();
		}
	}

}
