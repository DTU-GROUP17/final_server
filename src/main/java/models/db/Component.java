package models.db;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "component")
public class Component extends Model implements SoftDeletable<Component> {

	@Basic@Column(name = "name", nullable = false)
	private String name;

	@Basic@Column(name = "deleted_at", nullable = false)
	private Timestamp deletedAt;

	@ManyToOne@JoinColumn(name = "deleted_by", referencedColumnName = "id")
	private User deletedBy;

	@OneToMany(mappedBy = "component")
	private Collection<Material> materials;

}
