CREATE TABLE users
(
    id       bigserial PRIMARY KEY,
    login    VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL
)