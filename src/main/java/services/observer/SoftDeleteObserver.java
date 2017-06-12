package services.observer;

import models.db.timestamps.Deletable;
import models.db.timestamps.UserSoftDeleteable;
import org.hibernate.event.spi.*;

public class SoftDeleteObserver implements PreDeleteEventListener{

	@Override
	public boolean onPreDelete(PreDeleteEvent event) {
		return (event.getEntity() instanceof UserSoftDeleteable) || !(event.getEntity() instanceof Deletable);
	}
}
