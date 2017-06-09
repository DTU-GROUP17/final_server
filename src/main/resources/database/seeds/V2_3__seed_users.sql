INSERT INTO users (name, username, password) VALUES
  ('Iohan', 'strazz', ':)'),
  ('Hans', 'hax', '$2a$10$0LTM2R3b6gDcE6BNfe.6D.U5z9h2XFFVkcBuGVgBtQxTXkk7Scz4W'),
  ('Lene', 'erekt', 'q'),
  ('Lone', 'pew', 'e'),
  ('Farmeceut','farm','$2a$06$G6HyWXDMsIIaqQ67xEx6W.Jj/TliNrBFcjQd4t50lxPWMlpXibOQa');

INSERT INTO users_roles (user_id, role_id) VALUES
  (2, 1);

--farm user role
INSERT INTO users_roles (user_id, role_id) VALUES
  (2, 2);
