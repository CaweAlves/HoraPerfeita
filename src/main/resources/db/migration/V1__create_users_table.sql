CREATE TABLE users
(
    id                SERIAL PRIMARY KEY,
    name              VARCHAR(255) NOT NULL,
    username          VARCHAR(100) NOT NULL UNIQUE,
    email             VARCHAR(150),
    password          VARCHAR(255),
    created_date      DATE,
    modified_date     DATE,
    created_by        VARCHAR(255),
    modified_by       VARCHAR(255)
);