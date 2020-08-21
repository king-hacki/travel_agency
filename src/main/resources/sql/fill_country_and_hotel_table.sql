SET AUTOCOMMIT = OFF;
START TRANSACTION;

INSERT INTO User(id, email, firstName, lastName, password)
VALUES (1, 'test1@gmail.com', 'test', 'test', 'test');

INSERT INTO Country(id, name) VALUES (1, 'Ukraine');
INSERT INTO Country(id, name) VALUES (2, 'USA');

INSERT INTO Hotel(id, name, country_id) VALUES (1, 'Ukraine_Hotel_01', 1);
INSERT INTO Hotel(id, name, country_id) VALUES (2, 'Ukraine_Hotel_02', 1);

INSERT INTO Room(id, level, number, hotel_id) VALUES (1, 'STANDARD', 100, 1);
INSERT INTO Room(id, level, number, hotel_id) VALUES (2, 'STANDARD', 200, 2);

INSERT INTO Rent(id, endRentDate, startRentDate, status, room_id, user_id, nextRent_id)
VALUES (1, '2020-08-22', '2020-08-21', 'ACTIVE', 1, 1, 2);

INSERT INTO Rent(id, endRentDate, startRentDate, status, room_id, user_id)
VALUES (2, '2020-08-29', '2020-08-26', 'BOOKED', 1, 1);

COMMIT ;
