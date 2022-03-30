
create database spring;
use spring;

DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS BOOK;

CREATE TABLE `user` (
    `id` tinyint NOT NULL AUTO_INCREMENT,
    `name` varchar(20) DEFAULT NULL,
    `sex` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE BOOK (
  `id` bigint primary key AUTO_INCREMENT,
  ISBN VARCHAR(50) NOT NULL unique,
  NAME VARCHAR(100) NOT NULL,
  PRICE INT,
  count INT
);

CREATE TABLE `idcard` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `code` varchar(18) COLLATE utf8_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `person` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
    `age` int(11) DEFAULT NULL,
    `idcard_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `idcard_id` (`idcard_id`),
CONSTRAINT `idcard_id` FOREIGN KEY (`idcard_id`) REFERENCES `idcard` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `orders` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `order_sn` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
    `user_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `user_id` (`user_id`),
    CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE `product` (
    `id` bigint NOT NULL,
    `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
    `price` double DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE `orders_detail` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `orders_id` bigint DEFAULT NULL,
    `product_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `orders_id` (`orders_id`),
    KEY `product_id` (`product_id`),
    CONSTRAINT `orders_id` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`),
    CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
