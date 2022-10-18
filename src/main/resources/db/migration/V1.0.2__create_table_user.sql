CREATE TABLE users
(
    id           uuid,
    name         text unique not null,
    release_date date        not null,
    primary key (name)
)