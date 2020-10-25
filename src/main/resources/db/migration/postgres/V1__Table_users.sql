CREATE SEQUENCE users_id_seq;
CREATE TABLE users (id BIGINT PRIMARY KEY NOT NULL DEFAULT NEXTVAL('users_id_seq'), username VARCHAR(255), created TIMESTAMP, logged_in BOOLEAN);
ALTER SEQUENCE users_id_seq OWNED BY users.id;
