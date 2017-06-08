package models.db;

import java.sql.Timestamp;

public interface SoftDeletable<T> extends Deletable{

	Timestamp getDeletedAt();

	T setDeletedBy(User user);

	User getDeletedBy();

	default boolean isDeleted(){
		return this.getDeletedAt()!=null;
	}

}
