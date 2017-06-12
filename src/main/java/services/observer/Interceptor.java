package services.observer;


import models.db.timestamps.UserCreateable;
import models.db.timestamps.UserSoftDeleteable;
import models.db.timestamps.UserUpdateable;
import org.apache.commons.lang.ArrayUtils;
import org.hibernate.*;
import org.hibernate.type.Type;
import services.authentication.Authenticatable;

import java.io.Serializable;
import java.sql.Timestamp;

public class Interceptor extends EmptyInterceptor {
	private Authenticatable authenticatable;

	@Override
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if(entity instanceof UserSoftDeleteable && authenticatable!=null) {
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
		if (entity instanceof UserCreateable && authenticatable!=null) {
			int indexOfCreateBy = ArrayUtils.indexOf(propertyNames, "createdBy");
			state[indexOfCreateBy] = authenticatable;
			return true;
		}
		return false;
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
		if(entity instanceof UserUpdateable) {
			int indexOfUpdatedBy = ArrayUtils.indexOf(propertyNames, "updatedBy");
			currentState[indexOfUpdatedBy] = authenticatable;
			return  true;
		}
		return false;
	}
}
