package services.observer;

import models.db.Deletable;
import org.hibernate.event.spi.*;

public class SoftDeleteObserver implements PreDeleteEventListener{

	@Override
	public boolean onPreDelete(PreDeleteEvent event) {
		return !(event.getEntity() instanceof Deletable);
	}
}
