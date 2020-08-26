SET AUTOCOMMIT = OFF;
START TRANSACTION;

INSERT INTO Role(id, name) VALUES (1, 'ROLE_USER');
INSERT INTO Role(id, name) VALUES (2, 'ROLE_MANAGER');

INSERT INTO User(id, email, firstName, lastName, password, role_id)
VALUES (1, 'user1@gmail.com', 'user_1', 'user_1', '$2a$10$pkZ/2w5iIeYj0YbqBzq0oeItNxwvkrMVf9JG4gqlwVGa0Ot4wakEy', 1);

INSERT INTO User(id, email, firstName, lastName, password, role_id)
VALUES (2, 'user2@gmail.com', 'user_2', 'user_2', '$2a$10$uMqZ5U5pfWwKGKjC6soYouUgfA7Q.pYqhSpZ.cM6GTSW0GmkmbrgW', 1);

INSERT INTO User(id, email, firstName, lastName, password, role_id)
VALUES (3, 'admin@gmail.com', 'admin', 'admin', '$2a$10$zGeEfbhaBlvowTg.t9M3SOG8/gocWI4vEs3w12ZseA/qoJDUIEz/G', 2);

INSERT INTO Country(id, name) VALUES (1, 'Ukraine');
INSERT INTO Country(id, name) VALUES (2, 'USA');

INSERT INTO Hotel(id, name, country_id) VALUES (1, 'Ukraine_Hotel_01', 1);
INSERT INTO Hotel(id, name, country_id) VALUES (2, 'Ukraine_Hotel_02', 1);

INSERT INTO Room(id, level, number, hotel_id) VALUES (1, 'STANDARD', 100, 1);
INSERT INTO Room(id, level, number, hotel_id) VALUES (1, 'STANDARD', 200, 1);
INSERT INTO Room(id, level, number, hotel_id) VALUES (1, 'STANDARD', 300, 1);
INSERT INTO Room(id, level, number, hotel_id) VALUES (2, 'STANDARD', 200, 2);

COMMIT ;
