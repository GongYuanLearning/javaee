
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

