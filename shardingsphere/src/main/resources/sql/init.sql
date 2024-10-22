CREATE DATABASE ds CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
CREATE DATABASE ds_0 CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
CREATE DATABASE ds_1 CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

-- ds_0 \ ds_1
CREATE TABLE IF NOT EXISTS t_order_0 (order_id BIGINT NOT NULL, user_id BIGINT NOT NULL, status VARCHAR(45) NULL, PRIMARY KEY (order_id));
CREATE TABLE IF NOT EXISTS t_order_1 (order_id BIGINT NOT NULL, user_id BIGINT NOT NULL, status VARCHAR(45) NULL, PRIMARY KEY (order_id));
CREATE TABLE IF NOT EXISTS t_order_item_0 (item_id BIGINT NOT NULL, order_id BIGINT NOT NULL, user_id BIGINT NOT NULL, status VARCHAR(45) NULL, PRIMARY KEY (item_id));
CREATE TABLE IF NOT EXISTS t_order_item_1 (item_id BIGINT NOT NULL, order_id BIGINT NOT NULL, user_id BIGINT NOT NULL, status VARCHAR(45) NULL, PRIMARY KEY (item_id));
CREATE TABLE IF NOT EXISTS t_user_0 (id BIGINT NOT NULL, name VARCHAR(45) NULL, PRIMARY KEY (id));
CREATE TABLE IF NOT EXISTS t_user_1 (id BIGINT NOT NULL, name VARCHAR(45) NULL, PRIMARY KEY (id));
-- 广播表 ds \ ds_0 \ ds_1
CREATE TABLE IF NOT EXISTS t_broadcast_table (id BIGINT NOT NULL, name VARCHAR(45) NULL, status VARCHAR(45) NULL, PRIMARY KEY (id));

-- 单表 ds
CREATE TABLE IF NOT EXISTS t_single_table (id BIGINT NOT NULL, name VARCHAR(45) NULL, status VARCHAR(45) NULL, PRIMARY KEY (id));



CREATE TABLE IF NOT EXISTS t_order_auto_0 (order_id BIGINT NOT NULL, user_id BIGINT NOT NULL, status VARCHAR(45) NULL, PRIMARY KEY (order_id));
CREATE TABLE IF NOT EXISTS t_order_auto_1 (order_id BIGINT NOT NULL, user_id BIGINT NOT NULL, status VARCHAR(45) NULL, PRIMARY KEY (order_id));
CREATE TABLE IF NOT EXISTS t_order_item_auto_0 (item_id BIGINT NOT NULL, order_id BIGINT NOT NULL, user_id BIGINT NOT NULL, status VARCHAR(45) NULL, PRIMARY KEY (item_id));
CREATE TABLE IF NOT EXISTS t_order_item_auto_1 (item_id BIGINT NOT NULL, order_id BIGINT NOT NULL, user_id BIGINT NOT NULL, status VARCHAR(45) NULL, PRIMARY KEY (item_id));

CREATE TABLE IF NOT EXISTS t_config (id BIGINT NOT NULL AUTO_INCREMENT, status VARCHAR(45) NULL, PRIMARY KEY (id));


-- shardingsphere 数据库
CREATE TABLE `users` (
  `id` bigint NOT NULL COMMENT '用户ID',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  `phone_number` varchar(20) DEFAULT NULL COMMENT '手机号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户';

CREATE TABLE `orders` (
  `id` bigint NOT NULL COMMENT '订单ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `order_date` date DEFAULT NULL COMMENT '订单日期',
  `order_status` varchar(20) DEFAULT NULL COMMENT '订单状态',
  `total_amount` decimal(10,2) DEFAULT NULL COMMENT '订单总额',
  `shipping_address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';

CREATE TABLE `order_detail` (
  `id` bigint NOT NULL COMMENT '订单详情ID',
  `order_id` bigint DEFAULT NULL COMMENT '订单ID',
  `product_id` bigint DEFAULT NULL COMMENT '商品ID',
  `quantity` int DEFAULT NULL COMMENT '商品数量',
  `unit_price` decimal(10,2) DEFAULT NULL COMMENT '商品单价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单详情';





