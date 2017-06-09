package services.listener;


import org.apache.commons.lang.ArrayUtils;
import org.hibernate.*;
import org.hibernate.type.Type;
import services.authentication.Authenticatable;

import java.io.Serializable;
import java.util.Arrays;

public class Interceptor extends EmptyInterceptor {
	private Authenticatable authenticatable;

	public Interceptor(Authenticatable authenticatable) {
		this.authenticatable = authenticatable;
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		System.out.println("saved!");
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
		System.out.println("updated!");
		//int indexOfCreateBy = ArrayUtils.indexOf(propertyNames, "created_by");
		//currentState[indexOfCreateBy] = Integer.parseInt(authenticatable.getIdentifier());
		return false;
	}
}
