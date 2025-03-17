-- CREATING DATABASE

DROP DATABASE IF EXISTS `backend-order-register`;

CREATE DATABASE `backend-order-register`;

USE `backend-order-register`;

CREATE TABLE `users`
(
	`id` bigint AUTO_INCREMENT NOT NULL,
	`username` varchar(18) NOT NULL,
    `password` varchar(60) NOT NULL,
    `enabled` tinyint NOT NULL DEFAULT 1,
    CONSTRAINT `pk_user_id` primary key (`id`),
    CONSTRAINT `uq_username` UNIQUE (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `roles` 
(
	`id` bigint AUTO_INCREMENT NOT NULL,
    `name` varchar(45) NOT NULL,
    CONSTRAINT `pk_role_id` primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `users_roles`
(
	`user_id` bigint,
    `role_id` bigint,
    CONSTRAINT `fk_users` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`),
    CONSTRAINT `fk_roles` FOREIGN KEY (`role_id`) REFERENCES `roles`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `products`
(
	`id` bigint AUTO_INCREMENT NOT NULL,
    `product_id` binary(16),
    `name` varchar(45),
    `price` double,
    `currency` varchar(3),
    `state` boolean,
    CONSTRAINT `product_pk_id` PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `materials`
(
	`id` bigint AUTO_INCREMENT NOT NULL,
	`material_id` binary(16),
    `name` varchar(45),
    `price` double,
    `currency` varchar(3),
    `unit` varchar(25),
    `state` boolean,
    CONSTRAINT `material_pk_id` PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `products_detail`
(
	`id` bigint AUTO_INCREMENT NOT NULL,
	`product_id` bigint,
    `material_id` bigint,
    `quantity` int,
    CONSTRAINT `product_detail_pk_id` PRIMARY KEY (`id`),
    CONSTRAINT `product_detail_fk_product_id` FOREIGN KEY (`product_id`) REFERENCES `products`(`id`),
    CONSTRAINT `product_detail_fk_material_id` FOREIGN KEY (`material_id`) REFERENCES `materials`(`id`),
    CONSTRAINT `product_detail_unique_product_material_id` UNIQUE(`product_id`,`material_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `orders`
(
	`id` bigint AUTO_INCREMENT NOT NULL,
	`order_id` binary(16),
    `ruc` varchar(20),
    `date` date,
	`total` double,
    `currency_pen_to_usd` varchar(3),
    `exchange` double,
    `address` varchar(255),
    `state` boolean,
    CONSTRAINT `order_pk_id` PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `order_products`
(
	`id` bigint AUTO_INCREMENT NOT NULL,
    `product_id` bigint NOT NULL,
    `order_id` bigint,
    `name` varchar(45),
    `price` double,
    `currency` varchar(3),
    `quantity` double,
    CONSTRAINT `order_product_pk_id` PRIMARY KEY (`id`),
    CONSTRAINT `order_product_fk_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders`(`id`),
    CONSTRAINT `order_product_fk_product_id` FOREIGN KEY (`product_id`) REFERENCES `products`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `order_product_materials`
(
	`id` bigint AUTO_INCREMENT NOT NULL,
    `material_id` bigint NOT NULL,
    `order_product_id` bigint,
    `name` varchar(45),
    `price` double,
    `currency` varchar(3),
    `unit` varchar(25),
    `quantity` double,
    CONSTRAINT `order_product_material_pk_id` PRIMARY KEY (`id`),
    CONSTRAINT `order_product_material_fk_material_id` FOREIGN KEY (`material_id`) REFERENCES `materials`(`id`),
    CONSTRAINT `order_product_material_fk_order_product_id` FOREIGN KEY (`order_product_id`) REFERENCES `order_products`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

INSERT INTO roles (name) VALUES ("ROLE_USER"),("ROLE_ADMIN");

INSERT INTO materials (material_id,name,price,currency,unit,state) VALUES 
(0x000000000068656C6C6F20776F726C64,'carlos',29.99,"PEN","Kg",true),
(0x000000000068656C6C6F20776F726C78,'pablo',39.99,"USD","Un",true);

INSERT INTO products (product_id, name, price, currency, state) VALUES
(0x000000000068656C6C6F20776F726C64,'carlos',29.99,"PEN",true),
(0x000000000068656C6C6F20776F726C78,'pablo',39.99,"USD",true);

INSERT INTO products_detail (product_id,material_id,quantity) VALUES 
(1,1,15),
(1,2,25),
(2,1,35);