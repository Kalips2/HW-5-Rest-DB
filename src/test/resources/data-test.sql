DROP TABLE IF EXISTS holds;
DROP TABLE IF EXISTS goods;
DROP TABLE IF EXISTS manufacturers;
DROP TABLE IF EXISTS warehouses;

CREATE TABLE manufacturers
(
    code    BIGINT       NOT NULL AUTO_INCREMENT,
    name    VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    PRIMARY KEY (code)
)
    ENGINE = InnoDB;

CREATE TABLE goods
(
    code             BIGINT         NOT NULL AUTO_INCREMENT,
    name             VARCHAR(255)   NOT NULL,
    price            DECIMAL(38, 2) NOT NULL,
    manufacturers_id BIGINT,
    PRIMARY KEY (code),
    FOREIGN KEY (manufacturers_id) REFERENCES manufacturers (code)
)
    ENGINE = InnoDB;

CREATE TABLE warehouses
(
    code    BIGINT       NOT NULL AUTO_INCREMENT,
    address VARCHAR(255) NOT NULL,
    name    VARCHAR(255) NOT NULL,
    PRIMARY KEY (code)
) ENGINE = InnoDB;

CREATE TABLE holds
(
    goods_code     BIGINT         NOT NULL,
    warehouse_code BIGINT         NOT NULL,
    amount         DECIMAL(38, 2) NOT NULL,
    PRIMARY KEY (goods_code, warehouse_code),
    FOREIGN KEY (goods_code) REFERENCES goods (code),
    FOREIGN KEY (warehouse_code) REFERENCES warehouses (code)
) ENGINE = InnoDB;

INSERT INTO manufacturers(code, name, address)
VALUES (1, 'Київхліб', 'м. Київ, вул. Межигірська, 83'),
       (2, 'Кулиниичі', 'м. Київ, вул. Межигірська, 83'),
       (3, '1-й Київський молокозавод', 'м. Київ, вул. Жилянська, 47');

INSERT INTO goods(code, name, price, manufacturers_id)
VALUES (1, 'Батон білий', 14.3, 3),
       (2, 'Молоко українське', 13.2, 2),
       (3, 'Сир голандський', 231.1, 2),
       (4, 'Хліб білий', 12.8, 1),
       (5, 'Сирок плавлений', 23.2, 2),
       (6, 'Хліб житній', 10.3, 1),
       (7, 'Бородинський хліб', 13.17, 3),
       (8, 'Сир італійський', 231.1, 2),
       (9, 'Лаваш вірменський', 15.20, 1),
       (10, 'Пиріжок з капустою', 9.70, 2),
       (11, 'Пиріжок з вишнею', 9.70, 3);

INSERT INTO warehouses(code, name, address)
VALUES (1, 'Васильківський', 'м. Київ, вул. Васильківська, 36'),
       (2, 'Голосіївський', 'м. Київ, вул. Ломоносова, 89');

INSERT INTO holds(goods_code, warehouse_code, amount)
VALUES (1, 1, 11),
       (2, 2, 12),
       (1, 2, 2),
       (3, 2, 10),
       (9, 1, 9);




