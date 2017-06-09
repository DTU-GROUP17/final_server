package services.observer;


import models.db.SoftDeletable;
import models.db.Updateable;
import org.apache.commons.lang.ArrayUtils;
import org.hibernate.*;
import org.hibernate.type.Type;
import services.authentication.Authenticatable;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.io.Serializable;
import java.sql.Timestamp;

public class Interceptor extends EmptyInterceptor {
	private Authenticatable authenticatable;

	@Context SecurityContext secContext;


	@Override
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if(entity instanceof SoftDeletable) {
			int indexOfDeletedBy = ArrayUtils.indexOf(propertyNames, "deletedBy");
			int indexOfDeletedAt = ArrayUtils.indexOf(propertyNames, "deletedAt");
			state[indexOfDeletedBy] = authenticatable;
			state[indexOfDeletedAt] = new Timestamp(System.currentTimeMillis());
		}
	}

	public Interceptor(Authenticatable authenticatable) {
		this.authenticatable = authenticatable;
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		int indexOfCreateBy = ArrayUtils.indexOf(propertyNames, "createdBy");
		state[indexOfCreateBy] = authenticatable;
		return true;
	}

	/**
	 * Called when updating a model.
	 *
	 * @param entity
	 * @param id
	 * @param currentState
	 * @param previousState
	 * @param propertyNames
	 * @param types
	 * @return
	 * @throws CallbackException
	 */
	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException {
		if(entity instanceof Updateable) {
			int indexOfUpdatedBy = ArrayUtils.indexOf(propertyNames, "updatedBy");
			currentState[indexOfUpdatedBy] = authenticatable;
			return  true;
		}
		return false;
	}
}
