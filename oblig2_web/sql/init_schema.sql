DROP SCHEMA IF EXISTS party CASCADE;
CREATE SCHEMA party;

SET SEARCH_PATH TO 'party';

CREATE TABLE person
(
    phone char(8) PRIMARY KEY NOT NULL,
    firstname VARCHAR(30) NOT NULL,
    lastname VARCHAR(30) NOT NULL,
    gender VARCHAR NOT NULL
);