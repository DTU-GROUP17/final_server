CREATE TABLE materials (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  component_id INT UNSIGNED NOT NULL,
  supplier_id INT UNSIGNED NOT NULL,
  stocked DOUBLE NOT NULL,
  used DOUBLE NOT NULL DEFAULT 0.0,

  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by INT UNSIGNED,
  FOREIGN KEY (created_by) REFERENCES users(id),

  FOREIGN KEY (component_id) REFERENCES components(id),
  FOREIGN KEY (supplier_id) REFERENCES suppliers(id),
  PRIMARY KEY (id),
  CHECK (used <= stocked),
  CHECK (stocked > 0.0),
  CHECK (used >= 0.0)
)