package models.db.timestamps;

import models.db.User;

import java.sql.Timestamp;

public interface UserUpdateable<T> extends Updateable {
	T setUpdatedAt(Timestamp timestamp);
	Timestamp getUpdatedAt();

	T setUpdatedBy(User user);
	User getUpdatedBy();
}
