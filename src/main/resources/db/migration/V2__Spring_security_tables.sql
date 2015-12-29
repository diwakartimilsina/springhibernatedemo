CREATE TABLE users
(
  user_id serial NOT NULL,
  user_name character varying(100),
  CONSTRAINT user_id PRIMARY KEY (user_id)
);
  
  
CREATE TABLE roles
(
  role_id serial NOT NULL,
  role_name character varying(100),
  CONSTRAINT role_id PRIMARY KEY (role_id)
);

