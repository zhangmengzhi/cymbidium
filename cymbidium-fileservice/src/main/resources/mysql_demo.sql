-- cymbidium 示例脚本
-- 作者：张孟志
-- Email：104446930@qq.com
-- 日期：2016-01-23

-- 建库、建用户、授权相关语句已注释，请根据实际参考。
-- CREATE DATABASE `cymbidium` /*!40100 COLLATE 'utf8_unicode_ci' */
-- CREATE USER 'cymbidium'@'localhost' IDENTIFIED BY 'cymbidium';
-- GRANT USAGE ON *.* TO 'cymbidium'@'localhost';
-- GRANT SELECT, EXECUTE, SHOW VIEW, ALTER, ALTER ROUTINE, CREATE, CREATE ROUTINE, 
-- 		 CREATE TEMPORARY TABLES, CREATE VIEW, DELETE, DROP, EVENT, INDEX, INSERT, 
--		 REFERENCES, TRIGGER, UPDATE, LOCK TABLES  
--		 ON `cymbidium`.* TO 'cymbidium'@'localhost' WITH GRANT OPTION;
-- FLUSH PRIVILEGES;

-- --------------------------------------------------------------------------------
-- 储存文件信息
drop table if exists fileinfos;

create table fileinfos (
	id INT(3) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	file_name varchar(64) NOT NULL COMMENT '文件名',
	file_path varchar(128) NOT NULL COMMENT '文件路径',
	enduser_id BIGINT NOT NULL COMMENT '文件用户ID',
	group_id INT(3) COMMENT '文件用户组ID',
	save_date timestamp DEFAULT CURRENT_TIMESTAMP() COMMENT '保存日期',
	status varchar(4) DEFAULT 'No' COMMENT '是否有效',
	note varchar(255) COMMENT '备注'
);
