CREATE TABLE ingredients (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  component_id INT UNSIGNED NOT NULL,
  recipe_id INT UNSIGNED,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by INT UNSIGNED,
  amount DOUBLE CHECK (amount > 0),
  tolerance DOUBLE CHECK (tolerance > 0),
  PRIMARY Key (id),
  FOREIGN KEY (component_id) REFERENCES components(id),
  FOREIGN KEY (recipe_id) REFERENCES recipes(id),
  FOREIGN KEY (created_by) REFERENCES users(id)
)