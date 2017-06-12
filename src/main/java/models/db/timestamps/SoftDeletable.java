package models.db.timestamps;

import models.db.User;

import java.sql.Timestamp;

public interface SoftDeletable<T> extends Deletable {
	T setDeletedAt(Timestamp timestamp);
	Timestamp getDeletedAt();

	T setDeletedBy(User user);
	User getDeletedBy();

	default boolean isDeleted(){
		return this.getDeletedAt()!=null;
	}
}
