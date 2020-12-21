BEGIN;

DROP TABLE IF EXISTS products;
CREATE TABLE products (id bigint PRIMARY KEY auto_increment, title VARCHAR(255), cost int);
INSERT INTO products (title, cost) VALUES
('apple', 120),
('kiwi', 230),
('banana', 65),
('mango', 140);

DROP TABLE IF EXISTS users;
CREATE TABLE users (id bigint PRIMARY KEY auto_increment, name_users VARCHAR(255));
INSERT INTO users (id, name_users) VALUES
('user1'),
('user2'),
('user3');

DROP TABLE IF EXISTS sale;
CREATE TABLE sale (id_users bigint, FOREIGN KEY (id_users) REFERENCES users (id), id_product bigint, FOREIGN KEY (id_product) REFERENCES products (id), sale_cost int);
INSERT INTO sale (id_users, id_product, sale_cost) VALUES
(1, 2, 220),
(1, 4, 135),
(3, 1, 110),
(1, 2, 230);

COMMIT;