CREATE TABLE ingredients (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  recipe_id INT UNSIGNED NOT NULL,
  component_id INT UNSIGNED NOT NULL,
  amount DOUBLE CHECK (amount > 0),
  tolerance DOUBLE CHECK (tolerance > 0),
  PRIMARY Key (id),
  FOREIGN KEY (recipe_id) REFERENCES recipes(id),
  FOREIGN KEY (component_id) REFERENCES components(id)
)