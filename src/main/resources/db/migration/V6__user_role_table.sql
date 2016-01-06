CREATE TABLE user_role
(
  user_role_id serial NOT NULL,
  user_id integer,
  role_id integer,
  CONSTRAINT user_role_id PRIMARY KEY (user_role_id),
  CONSTRAINT role_id FOREIGN KEY (role_id)
      REFERENCES roles (role_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT user_id FOREIGN KEY (user_id)
      REFERENCES users (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)