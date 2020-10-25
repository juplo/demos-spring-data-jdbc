CREATE TABLE outbox(id BIGINT PRIMARY KEY AUTO_INCREMENT, key VARCHAR(127), value varchar(1023), issued timestamp);
