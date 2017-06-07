CREATE TABLE ingredients (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  recipe_id INT UNSIGNED NOT NULL,
  component_id INT UNSIGNED NOT NULL,
  amount DOUBLE CHECK (amount > 0),
  tolerence DOUBLE CHECK (tolerence > 0),

  PRIMARY Key (id)
)