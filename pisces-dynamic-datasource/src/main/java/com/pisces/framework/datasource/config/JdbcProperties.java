/**
 * Copyright (c) 2017 Pisces Technology Co., Ltd.
 */

package com.pisces.framework.datasource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;

/**
 * 
 * @author yangxh
 * @date 2019年1月26日 上午12:41:49
 */
@Component("jdbcProperties")
@ConfigurationProperties(prefix = "datasource.jdbc")
public class JdbcProperties extends HikariConfig {
}
