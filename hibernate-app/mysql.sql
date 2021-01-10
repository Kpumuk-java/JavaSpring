BEGIN;

DROP TABLE IF EXISTS product_repository CASCADE;
CREATE TABLE product_repository (id AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255), cost int);
INSERT INTO product_repository (title, cost) VALUES
('box', 10),
('sphere', 20),
('maul', 100),
('door', 50),
('camera', 500);

COMMIT;