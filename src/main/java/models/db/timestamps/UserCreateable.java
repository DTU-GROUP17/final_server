package models.db.timestamps;

import models.db.User;

import java.sql.Timestamp;

public interface UserCreateable<T> {
	T setCreatedAt(Timestamp timestamp);
	Timestamp getCreatedAt();

	T setCreatedBy(User user);
	User getCreatedBy();
}
