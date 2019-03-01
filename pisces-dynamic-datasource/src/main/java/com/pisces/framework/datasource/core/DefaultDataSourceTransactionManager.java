/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.datasource.core;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

import com.pisces.framework.datasource.service.DynamicDataSourceService;

@SuppressWarnings("serial")
@Order(2)
public class DefaultDataSourceTransactionManager extends DataSourceTransactionManager {
	@Autowired
	DynamicDataSourceService dynamicDataSourceService;
	
	public DefaultDataSourceTransactionManager(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		System.err.println("transaction: begin");
		dynamicDataSourceService.supendDataSource();
		dynamicDataSourceService.routeDefaultDataSource();
		super.doBegin(transaction, definition);
	}
	
	@Override
	protected void doCleanupAfterCompletion(Object transaction) {
		super.doCleanupAfterCompletion(transaction);
		dynamicDataSourceService.resumeDataSource();
		System.err.println("transaction: after");
	}
}
