SET AUTOCOMMIT = OFF;
START TRANSACTION;

INSERT INTO Country(id, name) VALUES (1, 'Ukraine');
INSERT INTO Country(id, name) VALUES (2, 'USA');

INSERT INTO Hotel(id, name, country_id) VALUES (1, 'Ukraine_Hotel_01', 1);
INSERT INTO Hotel(id, name, country_id) VALUES (2, 'Ukraine_Hotel_02', 1);

COMMIT ;
