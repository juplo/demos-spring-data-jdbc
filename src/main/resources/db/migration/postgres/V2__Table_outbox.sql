CREATE SEQUENCE outbox_id_seq;
CREATE TABLE outbox(id BIGINT PRIMARY KEY NOT NULL DEFAULT NEXTVAL('outbox_id_seq'), key VARCHAR(127), value varchar(1023), issued timestamp);
ALTER SEQUENCE outbox_id_seq OWNED BY outbox.id;
