package models.db;

import java.sql.Timestamp;

public interface SoftDeletable {

	Timestamp getDeletedAt();

	default boolean isDeleted(){
		return this.getDeletedAt()!=null;
	}

}
