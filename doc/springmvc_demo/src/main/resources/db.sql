USE `fish`;

DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
    `id`           INT(12)      NOT NULL AUTO_INCREMENT,
    `author`       VARCHAR(200) NOT NULL,
    `title`        VARCHAR(200) NOT NULL,
    `desc`         TEXT         NOT NULL,
    `press_desc`   VARCHAR(1000),
    `size`         VARCHAR(50),
    `pages`        INT(7),
    `print`        VARCHAR(50),
    `bookbinding`  VARCHAR(255),
    `count`        INT(7),
    `publish_time` DATETIME,
    `add_time`     DATETIME,
    `price`        DOUBLE       NOT NULL,
    `press_id`     INT(12)      NOT NULL REFERENCES press (id),
    `author_id`    INT(12)      NOT NULL  REFERENCES author (id),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `press`;
CREATE TABLE `press` (
    `id`       INT(12) NOT NULL AUTO_INCREMENT,
    `website`  VARCHAR(255),
    `desc`     TEXT,
    `add_time` DATETIME,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `author`;
CREATE TABLE `author` (
    `id`       INT(12)      NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(255) NOT NULL,
    `email`    VARCHAR(255),
    `website`  VARCHAR(255),
    `desc`     TEXT,
    `add_time` DATETIME,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
    `id`             INT(12) NOT NULL AUTO_INCREMENT,
    `title`          VARCHAR(255),
    `content`        TEXT,
    `add_time`       DATETIME,
    `last_edit_time` DATETIME,
    `tags`           VARCHAR(255),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
    `id`          INT(12) NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(255),
    `website`     VARCHAR(255),
    `desc`        VARCHAR(500),
    `regist_time` DATETIME,
    `add_time`    DATETIME,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `cate`;
CREATE TABLE `cate` (
    `id`       INT(12) NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(255),
    `add_time` DATETIME,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `msg`;
CREATE TABLE `msg` (
    `id`          INT(12) NOT NULL AUTO_INCREMENT,
    `email`       VARCHAR(255),
    `content`     TEXT,
    `create_time` DATETIME,
    `ip`          VARCHAR(255),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
    `id`       INT(12) NOT NULL AUTO_INCREMENT,
    `address`  VARCHAR(512),
    `name`     VARCHAR(255),
    `phone`    VARCHAR(15),
    `email`    VARCHAR(255),
    `note`     TEXT,
    `order_id` VARCHAR(255),
    `price`    FLOAT,
    `time`     DATETIME,
    PRIMARY KEY (id)
);
