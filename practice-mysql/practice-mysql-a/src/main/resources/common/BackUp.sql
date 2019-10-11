
CREATE TABLE `t_goods` (
  `id`            bigint(20)  unsigned NOT NULL           AUTO_INCREMENT,
  `name`       VARCHAR(64)   NOT NULL          COMMENT '商品名',
  `state`         tinyint(1)    NOT NULL  DEFAULT '1' COMMENT '状态 1 生效 0 删除',
  `spec`       VARCHAR(64)   NOT NULL          COMMENT '规格',
  PRIMARY KEY (`id`)
)ENGINE = INNODB COMMENT ='商品表';

CREATE TABLE `t_buyer_info` (
  `buyer_id`   bigint(20)  unsigned NOT NULL           AUTO_INCREMENT,
  `name`       VARCHAR(64)   NOT NULL          COMMENT '用户名',
  `state`         tinyint(1)    NOT NULL  DEFAULT '1' COMMENT '状态 1 生效 0 删除',
  `sex`       tinyint(1)   NOT NULL  DEFAULT '2' COMMENT '性别 1男 0女 2未知',
  `register_time`       datetime  DEFAULT NULL COMMENT '注册日期',
  PRIMARY KEY (`buyer_id`),
  UNIQUE KEY `t_buyer_info_unique_name` (`name`)
)ENGINE = INNODB COMMENT ='用户表';

CREATE TABLE `t_order_info` (
  `order_id`            bigint(20)  unsigned NOT NULL           AUTO_INCREMENT,
  `order_number`       VARCHAR(32)   NOT NULL          COMMENT '订单号',
  `buyer_id`        bigint(20)   NOT NULL          COMMENT '买家id',
  `order_status`         tinyint(2)    NOT NULL  DEFAULT '0' COMMENT '状态 0 进行中 1 完成 2 取消 3 已结算',
  `order_amount`       decimal(12,2)   NOT NULL          COMMENT '订单金额',
  `create_time`       datetime  DEFAULT NULL COMMENT '订单创建日期',
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `t_order_info_unique_order_number` (`order_number`),
  KEY `t_order_info_index_buyer_id` (`buyer_id`)
)ENGINE = INNODB COMMENT ='订单表';

CREATE TABLE `t_order_item` (
  `item_id`            bigint(20)  unsigned NOT NULL           AUTO_INCREMENT,
  `order_id`            bigint(20)   NOT NULL          COMMENT '订单id',
  `order_number`       VARCHAR(32)   NOT NULL          COMMENT '订单号',
  `goods_id`           bigint(20)   NOT NULL          COMMENT '商品id',
  `goods_number`       decimal(12,2)   NOT NULL          COMMENT '商品数量',
  `goods_price`        decimal(12,2)   NOT NULL          COMMENT '商品单价',
  `goods_amount`       decimal(12,2)   NOT NULL          COMMENT '单项总金额',
  PRIMARY KEY (`item_id`),
  KEY `t_order_item_index_order_id` (`order_id`)
)ENGINE = INNODB COMMENT ='订单详情表';
