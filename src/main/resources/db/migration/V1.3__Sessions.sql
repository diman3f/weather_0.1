CREATE TABLE sessions
(
    id        uuid PRIMARY KEY ,
    user_id    bigint NOT NULL,
    expires_At time   NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users (id)
)