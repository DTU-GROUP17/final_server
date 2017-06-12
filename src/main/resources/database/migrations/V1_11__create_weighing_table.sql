CREATE TABLE weighings (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  product_batch_id INT UNSIGNED NOT NULL,
  material_id INT UNSIGNED NOT NULL,
  weight_id INT UNSIGNED NOT NULL,
  amount DOUBLE CHECK (amount > 0),
  weighed_by INT UNSIGNED,
  weighed_at TIMESTAMP,

  FOREIGN KEY (weighed_by) REFERENCES users(id),
  FOREIGN KEY (product_batch_id) REFERENCES product_batches(id),
  FOREIGN KEY (material_id) REFERENCES materials(id),
  FOREIGN KEY (weight_id) REFERENCES weights(id),

  PRIMARY KEY (id)

)