/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.global.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pisces.framework.global.pojo.SysGlobalDO;

public interface SysGlobalDAO {
	
	List<SysGlobalDO> findList();
	
	SysGlobalDO findByKey(@Param("key") String key);
	
	int insert(SysGlobalDO gdo);
	
	int update(SysGlobalDO gdo);
	
	int deleteById(@Param("id") int id);
	
	int deleteByKey(@Param("key") String key);
}
