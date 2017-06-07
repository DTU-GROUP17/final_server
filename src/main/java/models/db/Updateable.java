package models.db;

import java.sql.Timestamp;

public interface Updateable<T> {

	Timestamp getUpdatedAt();

	T setUpdatedBy(User user);

	User getUpdatedBy();

}
