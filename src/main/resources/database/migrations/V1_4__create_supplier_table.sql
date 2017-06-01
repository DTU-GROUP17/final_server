CREATE TABLE suppliers (
  id INT UNSIGNED NOT NULL,
  name VARCHAR(255) NOT NULL,

  created_at TIMESTAMP NOT NULL,
  created_by INT UNSIGNED,
  updated_at TIMESTAMP,
  updated_by INT UNSIGNED,
  deleted_at TIMESTAMP,
  deleted_by INT UNSIGNED,
  FOREIGN KEY (created_by) REFERENCES users(id),
  FOREIGN KEY (updated_by) REFERENCES users(id),
  FOREIGN KEY (deleted_by) REFERENCES users(id),

  PRIMARY KEY (id)
)