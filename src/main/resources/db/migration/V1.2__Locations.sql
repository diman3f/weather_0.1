CREATE TABLE locations
(
    id        bigserial PRIMARY KEY,
    name      VARCHAR(50)      NOT NULL,
    user_id   bigint           NOT NULL,
    latitude  double precision NOT NULL,
    longitude double precision NOT NULL,
    CONSTRAINT uniq_lat_lon UNIQUE (latitude, longitude)
)