CREATE TABLE materials (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  component_id INT UNSIGNED NOT NULL,
  supplier_id INT UNSIGNED NOT NULL,
  stocked DOUBLE NOT NULL CHECK (stocked > 0),
  used DOUBLE CHECK (used > 0 && used <= stocked),

  created_at TIMESTAMP NOT NULL,
  created_by INT UNSIGNED,
  FOREIGN KEY (created_by) REFERENCES users(id),

  FOREIGN KEY (component_id) REFERENCES components(id),
  FOREIGN KEY (supplier_id) REFERENCES suppliers(id),
  PRIMARY Key (id)
)