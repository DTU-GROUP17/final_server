CREATE TABLE recipes_ingredients (
  recipe_id INT UNSIGNED NOT NULL,
  ingredient_id INT UNSIGNED NOT NULL UNIQUE,

  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

  FOREIGN KEY (recipe_id) REFERENCES recipes(id),
  FOREIGN KEY (ingredient_id) REFERENCES ingredients(id)
)