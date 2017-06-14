package models.db;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true, of = {})
@Entity
@Table(name = "roles")
public class Role extends Model {

	@Basic@Column(name = "name", nullable = false)
	@Getter@Setter private String name;

	@Basic
	@CreationTimestamp
	@Column(name = "created_at")
	@Getter@Setter private Timestamp createdAt;

}
