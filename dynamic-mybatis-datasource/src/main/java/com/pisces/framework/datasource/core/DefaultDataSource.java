/**
 * Copyright (c) 2017 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.datasource.core;

import com.pisces.framework.datasource.config.JdbcProperties;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * @author yangxh
 * @date 2019年1月26日 上午12:44:34
 */
public class DefaultDataSource extends HikariDataSource {
	
	public DefaultDataSource() {
		super();
	}
	
	public DefaultDataSource(JdbcProperties jdbcProperties) {
		super(jdbcProperties);
	}
}
