/**
 * Copyright (c) 2017 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.datasource.dao;

import java.util.List;

import com.pisces.framework.datasource.pojo.DataSourceBaseDO;
import com.pisces.framework.datasource.pojo.DataSourceDO;

public interface DataSourceDAO {
	
	DataSourceBaseDO selectBaseByTenant(Integer tenant);
	
	DataSourceBaseDO selectBaseByDomain(String domain);
	
	List<DataSourceBaseDO> selectBaseList();
	
    DataSourceDO selectByTenant(Integer tenant);
    
    DataSourceDO selectByDomain(String domain);
    
    List<DataSourceDO> selectList();
	
    int deleteByTenant(Integer tenant);

    int insert(DataSourceDO record);

    int insertSelective(DataSourceDO record);

    int updateByTenantSelective(DataSourceDO record);
    
    int updateByDomainSelective(DataSourceDO record);

    int updateByTenant(DataSourceDO record);
    
    int updateByDomain(DataSourceDO record);
}