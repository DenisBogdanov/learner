DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS lessons;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq
  START 100000;

CREATE TABLE users
(
  id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name              VARCHAR                 NOT NULL,
  email             VARCHAR                 NOT NULL,
  password          VARCHAR                 NOT NULL,
  registration_date TIMESTAMP DEFAULT now() NOT NULL,
  enabled           BOOL DEFAULT TRUE       NOT NULL,
  daily_goal        INTEGER DEFAULT 120     NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
  ON users (email);


CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


CREATE TABLE lessons (
  id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id         INTEGER   NOT NULL,
  start_date_time TIMESTAMP NOT NULL,
  description     TEXT      NOT NULL,
  duration        INT       NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX lessons_unique_user_datetime_idx
  ON lessons (user_id, start_date_time);