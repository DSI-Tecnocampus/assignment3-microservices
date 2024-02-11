DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(45) NOT NULL,
    `description` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);
