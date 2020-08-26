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
INSERT INTO Country(id, name) VALUES (3, 'Poland');

INSERT INTO Hotel(id, name, country_id) VALUES (1, 'Lviv Hotel', 1);
INSERT INTO Hotel(id, name, country_id) VALUES (2, 'Ivano-Frankivs Hotel', 1);
INSERT INTO Hotel(id, name, country_id) VALUES (3, 'Kiev Hotel', 1);
INSERT INTO Hotel(id, name, country_id) VALUES (4, 'Odessa Hotel', 1);
INSERT INTO Hotel(id, name, country_id) VALUES (5, 'Ukraine Hotel', 1);
INSERT INTO Hotel(id, name, country_id) VALUES (6, 'Nadiya Hotel', 1);

INSERT INTO Hotel(id, name, country_id) VALUES (7, 'Varshava Hotel', 2);
INSERT INTO Hotel(id, name, country_id) VALUES (8, 'Hostel', 2);
INSERT INTO Hotel(id, name, country_id) VALUES (9, 'Poland Hotel', 2);

INSERT INTO Hotel(id, name, country_id) VALUES (10, 'USA 1 Hotel', 3);
INSERT INTO Hotel(id, name, country_id) VALUES (11, 'USA 2 Hotel', 3);
INSERT INTO Hotel(id, name, country_id) VALUES (12, 'USA 3 Hotel', 3);

INSERT INTO Room(id, level, number, hotel_id) VALUES (1, 'STANDARD', 101, 1);
INSERT INTO Room(id, level, number, hotel_id) VALUES (2, 'STANDARD', 201, 1);
INSERT INTO Room(id, level, number, hotel_id) VALUES (3, 'STANDARD', 302, 1);
INSERT INTO Room(id, level, number, hotel_id) VALUES (4, 'STANDARD', 223, 1);

INSERT INTO Room(id, level, number, hotel_id) VALUES (5, 'STANDARD', 104, 2);
INSERT INTO Room(id, level, number, hotel_id) VALUES (6, 'STANDARD', 205, 2);
INSERT INTO Room(id, level, number, hotel_id) VALUES (7, 'STANDARD', 306, 2);
INSERT INTO Room(id, level, number, hotel_id) VALUES (8, 'STANDARD', 227, 2);

INSERT INTO Room(id, level, number, hotel_id) VALUES (9, 'STANDARD', 108, 3);

INSERT INTO Room(id, level, number, hotel_id) VALUES (10, 'STANDARD', 209, 4);

INSERT INTO Room(id, level, number, hotel_id) VALUES (11, 'STANDARD', 310, 5);

INSERT INTO Room(id, level, number, hotel_id) VALUES (12, 'STANDARD', 220, 6);
INSERT INTO Room(id, level, number, hotel_id) VALUES (13, 'STANDARD', 131, 6);

INSERT INTO Room(id, level, number, hotel_id) VALUES (14, 'STANDARD', 240, 7);
INSERT INTO Room(id, level, number, hotel_id) VALUES (15, 'STANDARD', 350, 7);
INSERT INTO Room(id, level, number, hotel_id) VALUES (16, 'STANDARD', 260, 7);

INSERT INTO Room(id, level, number, hotel_id) VALUES (17, 'STANDARD', 11, 8);
INSERT INTO Room(id, level, number, hotel_id) VALUES (18, 'STANDARD', 270, 8);

INSERT INTO Room(id, level, number, hotel_id) VALUES (19, 'STANDARD', 380, 9);
INSERT INTO Room(id, level, number, hotel_id) VALUES (20, 'STANDARD', 20, 9);

INSERT INTO Room(id, level, number, hotel_id) VALUES (21, 'STANDARD', 1, 9);

INSERT INTO Room(id, level, number, hotel_id) VALUES (22, 'STANDARD', 800, 10);
INSERT INTO Room(id, level, number, hotel_id) VALUES (23, 'STANDARD', 500, 10);
INSERT INTO Room(id, level, number, hotel_id) VALUES (24, 'STANDARD', 620, 10);

INSERT INTO Room(id, level, number, hotel_id) VALUES (25, 'STANDARD', 191, 11);
INSERT INTO Room(id, level, number, hotel_id) VALUES (26, 'STANDARD', 222, 11);
INSERT INTO Room(id, level, number, hotel_id) VALUES (27, 'STANDARD', 333, 11);
INSERT INTO Room(id, level, number, hotel_id) VALUES (28, 'STANDARD', 444, 11);

COMMIT ;
