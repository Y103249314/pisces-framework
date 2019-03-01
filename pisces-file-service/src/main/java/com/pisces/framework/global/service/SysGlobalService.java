/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.global.service;

import java.util.List;

import com.pisces.framework.global.pojo.SysGlobalDO;

public interface SysGlobalService {
	
	List<SysGlobalDO> findList();
	
	SysGlobalDO findByKey(String key);
	
	int insert(SysGlobalDO gdo);
	
	int update(SysGlobalDO gdo);
	
	int deleteById(int id);
	
	int deleteByKey(String key);
	
	/**
	 * 目前支持5种类型: int,string,double,array,map
	 * @param key
	 * @return
	 */
	<T> T getValue(String key);
	
	<T> T getValue(SysGlobalDO gdo);
}
