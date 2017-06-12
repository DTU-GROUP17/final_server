CREATE TABLE product_batches (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  recipe_id INT UNSIGNED NOT NULL,
  status ENUM('queued', 'active', 'complete') NOT NULL,

  created_at TIMESTAMP NOT NULL,
  created_by INT UNSIGNED,

  FOREIGN KEY (created_by) REFERENCES users(id),
  FOREIGN KEY (recipe_id) REFERENCES recipes(id),
  PRIMARY Key (id)
)