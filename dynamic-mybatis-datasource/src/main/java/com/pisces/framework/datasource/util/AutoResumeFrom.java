package com.pisces.framework.datasource.util;

import com.pisces.framework.datasource.service.DynamicDataSourceService;

/**
 * @author yangxh
 * @date 2018年9月12日 上午10:44:29
 */
public class AutoResumeFrom implements AutoCloseable {
	private DynamicDataSourceService dataSourceService;

	/**
	 * 挂起当前数据源，切换至指定租户数据源
	 * @param dataSourceService
	 * @param tenantId
	 */
	public AutoResumeFrom(DynamicDataSourceService dataSourceService, Integer tenantId) {
		this.dataSourceService = dataSourceService;
		this.dataSourceService.supendDataSource();
		this.dataSourceService.routeDataSource(tenantId);
	}
	
	/**
	 * 挂起当前数据源，切换至缺省数据源
	 * @param dataSourceService
	 */
	public AutoResumeFrom(DynamicDataSourceService dataSourceService) {
		this(dataSourceService, null);
	}
	
	@Override
	public void close() {
		this.dataSourceService.resumeDataSource();
	}
}
