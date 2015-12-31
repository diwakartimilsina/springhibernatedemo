ALTER TABLE users
ADD CONSTRAINT unique_email_address UNIQUE (email),
ADD CONSTRAINT unique_user_name UNIQUE (user_name)