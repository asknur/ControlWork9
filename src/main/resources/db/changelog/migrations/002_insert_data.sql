-- password: qwe
INSERT INTO users (email, password, full_name, account_type, enabled)
VALUES ('admin@email.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', 'Администратор',
        'ROLE_ADMIN', true),
       ('user1@email.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', 'Asan', 'ROLE_USER', true),
       ('user2@email.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', 'Ulan', 'ROLE_USER', true),
       ('user3@email.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', 'Aybek', 'ROLE_USER', true),
       ('tezjet@email.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', 'Tez jet', 'ROLE_COMPANY', true),
       ('asman@email.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', 'Asman', 'ROLE_COMPANY', true);


INSERT INTO flights (flight_number, departure_city, arrival_city, departure_time, arrival_time, company_id)
VALUES ('AM101', 'Bishkek', 'Moscow', '2026-06-15 08:00:00', '2026-06-15 10:30:00',
        (SELECT id FROM users WHERE email = 'tezjet@email.com')),
       ('FK202', 'Bishkek', 'Istanbul', '2026-06-20 14:00:00', '2026-06-20 17:45:00',
        (SELECT id FROM users WHERE email = 'asman@email.com'));


INSERT INTO tickets (flight_id, seat_class, price, seat_number, is_booked)
SELECT id, 'BUSINESS', 450.00, '1A', false
FROM flights
WHERE flight_number = 'AM101';
INSERT INTO tickets (flight_id, seat_class, price, seat_number, is_booked)
SELECT id, 'BUSINESS', 450.00, '1B', false
FROM flights
WHERE flight_number = 'AM101';
INSERT INTO tickets (flight_id, seat_class, price, seat_number, is_booked)
SELECT id, 'BUSINESS', 450.00, '1C', false
FROM flights
WHERE flight_number = 'AM101';
INSERT INTO tickets (flight_id, seat_class, price, seat_number, is_booked)
SELECT id, 'ECONOMY', 180.00, '10A', false
FROM flights
WHERE flight_number = 'AM101';
INSERT INTO tickets (flight_id, seat_class, price, seat_number, is_booked)
SELECT id, 'ECONOMY', 180.00, '10B', false
FROM flights
WHERE flight_number = 'AM101';
INSERT INTO tickets (flight_id, seat_class, price, seat_number, is_booked)
SELECT id, 'ECONOMY', 180.00, '10C', false
FROM flights
WHERE flight_number = 'AM101';
INSERT INTO tickets (flight_id, seat_class, price, seat_number, is_booked)
SELECT id, 'ECONOMY', 180.00, '11A', false
FROM flights
WHERE flight_number = 'AM101';
INSERT INTO tickets (flight_id, seat_class, price, seat_number, is_booked)
SELECT id, 'ECONOMY', 180.00, '11B', false
FROM flights
WHERE flight_number = 'AM101';
INSERT INTO tickets (flight_id, seat_class, price, seat_number, is_booked)
SELECT id, 'ECONOMY', 180.00, '11C', false
FROM flights
WHERE flight_number = 'AM101';
INSERT INTO tickets (flight_id, seat_class, price, seat_number, is_booked)
SELECT id, 'ECONOMY', 180.00, '12A', false
FROM flights
WHERE flight_number = 'AM101';


INSERT INTO tickets (flight_id, seat_class, price, seat_number, is_booked)
SELECT id, 'BUSINESS', 500.00, '1A', false
FROM flights
WHERE flight_number = 'FK202';
INSERT INTO tickets (flight_id, seat_class, price, seat_number, is_booked)
SELECT id, 'BUSINESS', 500.00, '1B', false
FROM flights
WHERE flight_number = 'FK202';
INSERT INTO tickets (flight_id, seat_class, price, seat_number, is_booked)
SELECT id, 'BUSINESS', 500.00, '1C', false
FROM flights
WHERE flight_number = 'FK202';
INSERT INTO tickets (flight_id, seat_class, price, seat_number, is_booked)
SELECT id, 'ECONOMY', 200.00, '10A', false
FROM flights
WHERE flight_number = 'FK202';
INSERT INTO tickets (flight_id, seat_class, price, seat_number, is_booked)
SELECT id, 'ECONOMY', 200.00, '10B', false
FROM flights
WHERE flight_number = 'FK202';
INSERT INTO tickets (flight_id, seat_class, price, seat_number, is_booked)
SELECT id, 'ECONOMY', 200.00, '10C', false
FROM flights
WHERE flight_number = 'FK202';
INSERT INTO tickets (flight_id, seat_class, price, seat_number, is_booked)
SELECT id, 'ECONOMY', 200.00, '11A', false
FROM flights
WHERE flight_number = 'FK202';
INSERT INTO tickets (flight_id, seat_class, price, seat_number, is_booked)
SELECT id, 'ECONOMY', 200.00, '11B', false
FROM flights
WHERE flight_number = 'FK202';
INSERT INTO tickets (flight_id, seat_class, price, seat_number, is_booked)
SELECT id, 'ECONOMY', 200.00, '11C', false
FROM flights
WHERE flight_number = 'FK202';
INSERT INTO tickets (flight_id, seat_class, price, seat_number, is_booked)
SELECT id, 'ECONOMY', 200.00, '12A', false
FROM flights
WHERE flight_number = 'FK202';


INSERT INTO bookings (user_id, ticket_id, booked_at)
SELECT (SELECT id FROM users WHERE email = 'user1@email.com'),
       (SELECT id
        FROM tickets
        WHERE seat_number = '10A'
          AND flight_id = (SELECT id FROM flights WHERE flight_number = 'AM101')),
       CURRENT_TIMESTAMP;

INSERT INTO bookings (user_id, ticket_id, booked_at)
SELECT (SELECT id FROM users WHERE email = 'user2@email.com'),
       (SELECT id
        FROM tickets
        WHERE seat_number = '1A'
          AND flight_id = (SELECT id FROM flights WHERE flight_number = 'FK202')),
       CURRENT_TIMESTAMP;