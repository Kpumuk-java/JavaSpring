create table products (
        id                          bigserial primary key,
        title                       varchar(255),
        price                       int,
        created_at                  timestamp default current_timestamp,
        updated_at                  timestamp default current_timestamp
        );
insert into products (title, price) values
('apple', 102),
('orange', 200),
('banana', 78),
('pineapple', 300),
('kiwi', 88),
('mango', 130),
('pear', 50),
('lemon', 175),
('grapes', 248),
('plum', 198);