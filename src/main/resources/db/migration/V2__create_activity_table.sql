CREATE TABLE activity
(
    id                  SERIAL PRIMARY KEY,
    name                VARCHAR(255),
    minimum_temperature DECIMAL(8, 4),
    maximum_temperature DECIMAL(8, 4),
    created_date        DATE,
    modified_date       DATE,
    created_by          VARCHAR(255),
    modified_by         VARCHAR(255)
);