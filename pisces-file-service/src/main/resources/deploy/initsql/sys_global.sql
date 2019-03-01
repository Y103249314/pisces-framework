CREATE TABLE `sys_global` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `key` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '配置项键(唯一)',
  `value` VARCHAR(512) NOT NULL DEFAULT '' COMMENT '配置项值(array格式为"v1,v2,...", map格式为"key:val,...")',
  `type` VARCHAR(8) NOT NULL DEFAULT 'string' COMMENT '数据类型(int,string,double,array,map)',
  `description` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '描述(中文名称或说明)',
  `create_by` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '创建者ID',
  `create_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_by` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '更新者ID',
  `update_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_KEY` (`key`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统全局配置表'