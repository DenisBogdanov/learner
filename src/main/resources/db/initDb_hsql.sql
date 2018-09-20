DROP TABLE user_roles
IF EXISTS;
DROP TABLE lessons
IF EXISTS;
DROP TABLE users
IF EXISTS;
DROP SEQUENCE global_seq
IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ
  AS INTEGER
  START WITH 100000;

CREATE TABLE users
(
  id                INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name              VARCHAR(255)            NOT NULL,
  email             VARCHAR(255)            NOT NULL,
  password          VARCHAR(255)            NOT NULL,
  registration_date TIMESTAMP DEFAULT now() NOT NULL,
  enabled           BOOLEAN DEFAULT TRUE    NOT NULL,
  daily_goal        INTEGER DEFAULT 120     NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
  ON USERS (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES USERS (id)
    ON DELETE CASCADE
);

CREATE TABLE lessons
(
  id              INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  user_id         INTEGER      NOT NULL,
  start_date_time TIMESTAMP    NOT NULL,
  description     VARCHAR(255) NOT NULL,
  duration        INT          NOT NULL,
  FOREIGN KEY (user_id) REFERENCES USERS (id)
    ON DELETE CASCADE
);
CREATE UNIQUE INDEX lessons_unique_user_datetime_idx
  ON lessons (user_id, start_date_time)