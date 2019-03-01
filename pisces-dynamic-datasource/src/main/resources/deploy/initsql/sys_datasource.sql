CREATE TABLE `sys_datasource` (
  `tenant` INT(11) NOT NULL DEFAULT '0' COMMENT '租户ID',
  `domain` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '租户域名',
  `name` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '租户名称(冗余字段)',
  `url` VARCHAR(128) NOT NULL DEFAULT 'jdbc:mysql://192.168.1.128:3307/pisces_shard_{tenant}' COMMENT '数据源URL(不含参数)',
  `username` VARCHAR(32) NOT NULL DEFAULT 'ourchem_ipman' COMMENT '用户名',
  `password` VARCHAR(32) NOT NULL DEFAULT 'ourchem_ipman' COMMENT '密码',
  `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态(0:正常,-1:删除)',
  `group` VARCHAR(32) NOT NULL DEFAULT '0' COMMENT '分组ID',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` VARCHAR(32) NOT NULL DEFAULT '0' COMMENT '创建者ID',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` VARCHAR(32) NOT NULL DEFAULT '0' COMMENT '更新者ID',
  PRIMARY KEY (`tenant`),
  UNIQUE KEY `DOMAIN` (`domain`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='租户数据源配置'