drop table if exists user;
-- 用户表
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名（长度50以内，唯一）',
    password VARCHAR(50) NOT NULL COMMENT '用户密码（加密存储）',
    email VARCHAR(50) NOT NULL UNIQUE COMMENT '用户邮箱（唯一标识）'
) COMMENT '系统用户信息表';

drop table  if exists `history`;
CREATE TABLE `history` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '记录ID',
    `user_id` INT NOT NULL COMMENT '关联用户ID',
    `content` VARCHAR(256) NOT NULL COMMENT '操作内容',
    `time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间（自动生成）'
) COMMENT '用户操作历史记录表';


drop table if exists subscription;
-- 订阅表
CREATE TABLE subscription (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '订阅ID',
    user_id INT NOT NULL COMMENT '关联用户ID',
    description VARCHAR(255) NOT NULL COMMENT '订阅商品描述',
    img VARCHAR(255) NOT NULL COMMENT '商品图片链接',
    shop_name VARCHAR(255) DEFAULT NULL COMMENT '店铺名称',
    platform VARCHAR(255) DEFAULT NULL COMMENT '所属平台',
    price DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '商品价格',
    detail_url VARCHAR(255) DEFAULT NULL COMMENT '商品详情页链接'
) COMMENT '商品订阅信息表';

drop table if exists notice;
-- 通知表
CREATE TABLE notice (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '通知ID',
    user_id INT NOT NULL COMMENT '关联用户ID',
    description VARCHAR(255) NOT NULL COMMENT '通知内容',
    img VARCHAR(255) NOT NULL COMMENT '商品图片链接',
    shop_name VARCHAR(255) DEFAULT NULL COMMENT '店铺名称',
    present_price DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '当前价格',
    target_price DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '目标价格',
    detail_url VARCHAR(255) DEFAULT NULL COMMENT '商品详情页链接',
    is_notice BOOLEAN NOT NULL DEFAULT FALSE COMMENT '是否已通知（默认未通知）'
) COMMENT '价格变动通知表';




