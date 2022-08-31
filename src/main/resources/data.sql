DELETE FROM votes;
DELETE FROM dishes;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM users;

INSERT INTO users(name, email, password)
VALUES ('admin', 'admin@gmail.com', '{noop}admin123'),
       ('user', 'user@yandex.ru', '{noop}user11');

INSERT INTO user_roles(user_id, role)
VALUES (1, 'ADMIN'),
       (1, 'USER'),
       (2, 'USER');

INSERT INTO restaurants(name, description, address)
VALUES ('restaurant_1', 'description_1', 'address_1'),
       ('restaurant_2', 'description_2', 'address_2'),
       ('restaurant_3', 'description_3', 'address_3');

INSERT INTO dishes(restaurant_id, name, price, added)
VALUES (1, 'restaurant_1 dish_1', '150.00', default),
       (1, 'restaurant_1 dish_2', '215.35', default),
       (1, 'restaurant_1 dish_3', '115.30', default),
       (1, 'restaurant_1 dish_4', '99.99', default),
       (2, 'restaurant_2 dish_1', '100.00', default),
       (2, 'restaurant_2 dish_2', '120.56', default),
       (2, 'restaurant_2 dish_3', '110.00', default),
       (3, 'restaurant_3 dish_1', '50.00', '2022-05-26'),
       (3, 'restaurant_3 dish_2', '80.60', '2022-05-26'),
       (3, 'restaurant_3 dish_3', '70.50', '2022-05-26');

INSERT INTO votes(user_id, restaurant_id, added)
VALUES (1, 1, '2022-05-25'),
       (2, 2, default),
       (2, 2, '2022-05-25');