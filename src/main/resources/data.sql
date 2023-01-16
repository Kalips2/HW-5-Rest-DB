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
       (3, '1-й Київський молокозавод', 'м. Київ, вул. Жилянська, 47'),
       (4, 'Хлібна справа', 'м. Київ, вул. Мілютенка, 21'),
       (5, 'Ла Тарта', 'м. Київ, в. Чернігівська, 10'),
       (6, 'Інвест Плюс', 'м. Київ, в. Михайла Бойчука, 9'),
       (7, 'Сироман', 'м. Київ, в. Луценка Дмитра, 14-А'),
       (8, 'Доообра ферма', 'м. Київ, в. Шовковича, 50-А'),
       (9, 'ВКФ Столичний Хлібзавод', 'м. Київ, в. Червоноткацька, 20'),
       (10, 'Макаронс', 'м. Київ, в. Героїв Дніпра, 32');

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
       (10, 'Сухарі звичайні', 23.2, 5),
       (11, 'Борошно пекарське звичайне', 16.2, 6),
       (12, 'Пиріжок з капустою', 9.70, 2),
       (13, 'Торт Київський', 302.70, 2),
       (14, 'Масло вершкове 56%', 49.20, 7),
       (15, 'Круасан з вишневою начинкою', 10.50, 1),
       (16, 'Крем творожний', 30.66, 6),
       (17, 'Какао 70%', 29.90, 5),
       (18, 'Торт "Наполеон"', 200.50, 4),
       (19, 'Сир Ранковий', 145.50, 8),
       (20, 'Хліб тостовий світлий', 19.40, 1),
       (21, 'Пудра цукрова', 12.20, 5),
       (22, 'Сир легкий', 170.20, 7),
       (23, 'Печиво "Весняне"', 125.40, 4),
       (24, 'Сир "Янгол"', 220.30, 8),
       (25, 'Пластівці натуральні', 30.20, 4),
       (26, 'Хліб вечірній', 8.90, 9),
       (27, 'Олія соняшникова, природня', 30.70, 10),
       (28, 'Хліб здібний', 9.50, 5),
       (29, 'Хліб пишний', 9.90, 6),
       (30, 'Чіпси з крабовим смаком', 20.40, 4),
       (31, 'Шоколад гіркий, чорний', 20.20, 10),
       (32, 'Молоко домашнє', 30.20, 7);

INSERT INTO warehouses(code, name, address)
VALUES (1, 'Васильківський', 'м. Київ, вул. Васильківська, 36'),
       (2, 'Голосіївський', 'м. Київ, вул. Ломоносова, 89'),
       (3, 'Святошинський', 'м. Київ, вул. Вербівська, 20'),
       (4, 'Шевченковський', 'м. Київ, вул. Десятинська, 2'),
       (5, 'Оболонський', 'м. Київ, вул. Героїв Дніпра, 5-А'),
       (6, 'Дарницький', 'м. Київ, Лісовий провулок, 3'),
       (7, 'Печерський', 'м. Київ, вул. Велика Васильківська, 12');

INSERT INTO holds(goods_code, warehouse_code, amount)
VALUES (1, 1, 11),
       (2, 1, 12),
       (1, 2, 2),
       (4, 4, 3),
       (7, 3, 10),
       (8, 7, 13),
       (9, 6, 20),
       (10, 7, 1),
       (30, 2, 10),
       (6, 1, 9),
       (18, 1, 11),
       (15, 3, 12),
       (11, 4, 20),
       (3, 5, 21),
       (11, 5, 13),
       (12, 1, 14),
       (22, 3, 8),
       (19, 6, 3),
       (31, 5, 23),
       (3, 1, 11),
       (17, 4, 9),
       (5, 7, 10),
       (27, 6, 10),
       (27, 2, 2),
       (16, 7, 9);




