CREATE TABLE Sessions
(
    id        uuid,
    user_Id    bigint NOT NULL,
    expires_At time   NOT NULL,
    FOREIGN KEY (user_Id) REFERENCES Users (id)
)