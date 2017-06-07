package models.db;

import java.sql.Timestamp;

public interface SoftDeletable<T> {

	Timestamp getDeletedAt();

	T setDeletedBy(User user);

	User getDeletedBy();

	default boolean isDeleted(){
		return this.getDeletedAt()!=null;
	}

}
