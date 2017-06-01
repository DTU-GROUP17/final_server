CREATE TABLE ingredients (
  recipe_id INT UNSIGNED NOT NULL,
  component_id INT UNSIGNED NOT NULL,
  amount DOUBLE CHECK (amount > 0),
  tolerence DOUBLE CHECK (tolerence > 0),

  PRIMARY Key (recipe_id, component_id)
)