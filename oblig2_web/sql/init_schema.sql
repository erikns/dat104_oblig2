DROP SCHEMA IF EXISTS party CASCADE;
CREATE SCHEMA party;

SET SEARCH_PATH TO 'party';

CREATE TABLE person
(
    phone char(8) PRIMARY KEY NOT NULL,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    gender VARCHAR NOT NULL
);