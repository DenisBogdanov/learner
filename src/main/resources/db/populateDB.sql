DELETE FROM user_roles;
DELETE FROM lessons;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;


INSERT INTO users (name, email, password) VALUES
  ('user', 'user@gmail.com', 'password'),
  ('admin', 'admin@gmail.com', 'admin');


INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);


INSERT INTO lessons (start_date_time, description, duration, user_id)
VALUES ('2018-09-14 08:30:00', 'Java', 60, 100000),
  ('2018-09-14 21:30:00', 'Java', 30, 100000),
  ('2018-09-15 12:30:00', 'Java', 60, 100000),
  ('2018-09-15 16:30:00', 'Java', 60, 100000),
  ('2018-09-15 19:00:00', 'Java', 60, 100000),
  ('2018-09-16 14:00:00', 'Java', 30, 100001),
  ('2018-09-16 21:00:00', 'Java', 60, 100001);