DROP TABLE IF EXISTS test_schema.users;

CREATE TABLE test_schema.users
(
    id       bigserial PRIMARY KEY,
    login    VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL
);