/**
 * Copyright (c) 2017 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.datasource.config;


import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.pisces.framework.datasource.core.DefaultDataSource;
import com.pisces.framework.datasource.core.DefaultDataSourceTransactionManager;
import com.pisces.framework.datasource.core.DynamicDataSource;

/**
 * @author yangxh
 * @date 2018年8月13日 上午10:54:13
 */
@Configuration
@MapperScan(basePackages= {"com.pisces.**.dao"})
public class MybatisConfig {
	
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactoryBean sqlSessionFactory(DynamicDataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/pisces/**/*.xml"));
		return sqlSessionFactoryBean;
	}

	@Bean
	public DynamicDataSource dynamicDataSource(JdbcProperties jdbcProperties) {
		DefaultDataSource defaultDataSource = new DefaultDataSource(jdbcProperties);
		return new DynamicDataSource(defaultDataSource);
	}

	@Primary
	@Bean("transactionManager")
	public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean("defaultDataSourceTransactionManager")
	public DefaultDataSourceTransactionManager defaultDataSourceTransactionManager(DynamicDataSource dataSource) {
		return new DefaultDataSourceTransactionManager(dataSource);
	}

	//@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.pisces.**.dao");
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		return mapperScannerConfigurer;
	}
}
