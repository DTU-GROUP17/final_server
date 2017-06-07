package models.db;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class Model{
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
}
