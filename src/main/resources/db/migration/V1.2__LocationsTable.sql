CREATE TABLE Locations
(
    id        bigserial PRIMARY KEY,
    name      VARCHAR(50) NOT NULL,
    user_id   bigint      NOT NULL,
    latitude  real        NOT NULL,
    longitude real        NOT NULL,
    CONSTRAINT uniq_lat_lon UNIQUE (latitude, longitude),
    FOREIGN KEY (user_id) REFERENCES Users (id)
)