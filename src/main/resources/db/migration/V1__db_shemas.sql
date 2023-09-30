CREATE TABLE users
(
    id          SERIAL PRIMARY KEY,
    email       VARCHAR(64)   NOT NULL UNIQUE,
    password    VARCHAR(2048) NOT NULL,
    role        VARCHAR(32)   NOT NULL,
    first_name  VARCHAR(64)   NOT NULL,
    last_name   VARCHAR(64)   NOT NULL,
    enabled     BOOLEAN       NOT NULL DEFAULT FALSE,
    create_date TIMESTAMP     NOT NULL,
    modify_date TIMESTAMP     NOT NULL
);