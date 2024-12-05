CREATE TABLE activity
(
    id                  SERIAL PRIMARY KEY,
    name                VARCHAR(255),
    minimum_temperature DECIMAL(8, 4),
    maximum_temperature DECIMAL(8, 4)
);