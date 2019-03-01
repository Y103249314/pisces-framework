/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.datasource.service;

import java.util.List;

import com.pisces.framework.datasource.core.DefaultDataSource;
import com.pisces.framework.datasource.pojo.DataSourceDO;

/**
 * @author yangxh
 * @date 2018年8月14日 下午1:54:20
 */
public interface DynamicDataSourceService {
	
	/**
	 * 刷新/初始化数据源池
	 */
	void refreshDataSourcePool(); 
	
	/**
	 * 切换数据源
	 * @param tenant
	 */
	void routeDataSource(Integer tenant);
	
	/**
	 * 切换到缺省数据源
	 */
	void routeDefaultDataSource();
	
	/**
	 * 获取已配置数据源的所有租户
	 * @return
	 */
	List<Integer> getAllTenants();
	
	/**
	 * 获取缺省数据源
	 */
	DefaultDataSource getDataSource(Integer tenant);
	
	/**
	 * 获取缺省数据源
	 */
	DefaultDataSource getDefaultDataSource();

	/**
	 * 挂起当前数据源
	 */
	void supendDataSource();

	/**
	 * 恢复之前挂起的数据源
	 */
	void resumeDataSource();
	
	/**
	 * 新增数据源
	 * @param ds
	 * @return
	 */
	int insertDataSource(DataSourceDO ds);
	
	/**
	 * 获取数据源: 按租户ID
	 * @param tenantId
	 * @return
	 */
	DataSourceDO selectDataSourceByTenant(Integer tenantId);
	
	/**
	 * 获取数据源: 按租户域名
	 * @param url
	 * @return
	 */
	DataSourceDO selectDataSourceByDomain(String domain);
	
	/**
	 * 按租户ID更新数据源
	 * @param ds
	 * @return
	 */
	int updateDataSourceByTenant(DataSourceDO ds);
	
	/**
	 * 按租户域名更新数据源
	 * @param ds
	 * @return
	 */
	int updateDataSourceByDomain(DataSourceDO ds);
	
	/**
	 * 删除指定租户的数据源
	 * @param tenant
	 * @return
	 */
	int deleteDataSource(int tenant);
}
