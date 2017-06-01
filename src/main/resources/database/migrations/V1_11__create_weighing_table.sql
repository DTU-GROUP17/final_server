CREATE TABLE weighings (
  id INT UNSIGNED NOT NULL,
  product_batch_id INT UNSIGNED NOT NULL,
  material_id INT UNSIGNED NOT NULL,
  weight_id INT UNSIGNED NOT NULL,
  amount DOUBLE CHECK (amount > 0),

  FOREIGN KEY (product_batch_id) REFERENCES product_batches(id),
  FOREIGN KEY (material_id) REFERENCES materials(id),
  FOREIGN KEY (weight_id) REFERENCES weights(id),

  PRIMARY KEY (id)
)