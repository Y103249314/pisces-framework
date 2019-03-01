/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.global.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pisces.framework.global.dao.SysGlobalDAO;
import com.pisces.framework.global.pojo.SysGlobalDO;

@Service
@Transactional(transactionManager = "defaultDataSourceTransactionManager", propagation = Propagation.REQUIRES_NEW)
public class SysGlobalServiceImpl implements SysGlobalService {
	
	@Autowired
	SysGlobalDAO dao;
	
	@Override
	public List<SysGlobalDO> findList() {
		return dao.findList();
	}

	@Override
	public SysGlobalDO findByKey(String key) {
		return dao.findByKey(key);
	}

	@Override
	public int insert(SysGlobalDO gdo) {
		return dao.insert(gdo);
	}

	@Override
	public int update(SysGlobalDO gdo) {
		return dao.update(gdo);
	}

	@Override
	public int deleteById(int id) {
		return dao.deleteById(id);
	}

	@Override
	public int deleteByKey(String key) {
		return dao.deleteByKey(key);
	}

	@Override
	public <T> T getValue(String key) {
		return getValue(findByKey(key));
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getValue(SysGlobalDO gdo) {
		if (gdo == null) {
			return null;
		}
		/**
		 * 注意：对值基本未做格式校验，在配置时要注意
		 */
		if (StringUtils.isNotBlank(gdo.getType()) && StringUtils.isNotBlank(gdo.getValue())) {
			switch(gdo.getType()) {
			case "int": 
				return (T)Integer.valueOf(gdo.getValue());
			case "double": 
				return (T)Double.valueOf(gdo.getValue());
			case "array": 
				return (T)gdo.getValue().split(",");
			case "map":
				return (T)Arrays.stream(gdo.getValue().split(","))
					.map(e->e.split(":"))
					.filter(kv->kv.length >= 2)
					.collect(Collectors.toMap(kv->kv[0], kv->kv[1]));
			}
		}
		return (T)gdo.getValue();
	}
}
