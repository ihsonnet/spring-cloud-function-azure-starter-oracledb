CREATE TABLE IF NOT EXISTS users (
                      id BIGINT PRIMARY KEY,
                      name VARCHAR(255),
                      email VARCHAR(255),
                      phone VARCHAR(20),
                      address TEXT
);

CREATE SEQUENCE IF NOT EXISTS users_id_seq INCREMENT 1 START 1;