/**
 * Copyright (c) 2017 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.datasource.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pisces.framework.datasource.core.DefaultDataSource;
import com.pisces.framework.datasource.core.DynamicDataSource;
import com.pisces.framework.datasource.core.DynamicDataSourceContext;
import com.pisces.framework.datasource.core.DynamicDataSourceHolder;
import com.pisces.framework.datasource.dao.DataSourceDAO;
import com.pisces.framework.datasource.pojo.DataSourceBaseDO;
import com.pisces.framework.datasource.pojo.DataSourceDO;
import com.pisces.framework.datasource.util.AutoLock;
import com.pisces.framework.datasource.util.AutoResumeFrom;

@Service
public class DynamicDataSourceServiceImpl implements DynamicDataSourceService {
	
	@Autowired
	DynamicDataSource dynamicDataSource;
	
	@Autowired
	DataSourceDAO dataSourceDAO;

	private final static ConcurrentHashMap<Integer, String> lookupKeyCache = new ConcurrentHashMap<Integer, String>(); //数据源LookupKey查找表缓存
	private final static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	private final static ThreadLocal<Stack<Integer>> supendedTenant = new ThreadLocal<Stack<Integer>>();
		
	@Override
	public void refreshDataSourcePool() {
		try (AutoResumeFrom resumer = new AutoResumeFrom(this)) {
			Optional.ofNullable(dataSourceDAO.selectBaseList()).ifPresent(dsDOList->{
				dynamicDataSource.refreshDataSource(dsDOList);
				try (AutoLock wlock = new AutoLock(rwLock.writeLock())) {
					lookupKeyCache.clear();
					dsDOList.parallelStream().forEach(dsDO->{
						lookupKeyCache.put(dsDO.getTenant(), dsDO.getUrl());
					});
				}
			});
		}
	}
	
	@Override
	public void routeDataSource(Integer tenant) {
		try (AutoLock rlock = new AutoLock(rwLock.readLock())) {
			if (tenant != null && lookupKeyCache.containsKey(tenant)) {
				DynamicDataSourceHolder.setDataSource(lookupKeyCache.get(tenant));
				DynamicDataSourceContext.setCurrentTenant(tenant);
			} else {
				routeDefaultDataSource();
			}
		} 
	}
	
	@Override
	public void routeDefaultDataSource() {
		DynamicDataSourceHolder.clearDataSource(); 
		DynamicDataSourceContext.removeCurrentTenant();
	}

	@Override
	public List<Integer> getAllTenants() {
		try (AutoLock rlock = new AutoLock(rwLock.readLock())) {
			return Collections.list(lookupKeyCache.keys());
		} 
	}

	@Override
	public DefaultDataSource getDataSource(Integer tenant) {
		try (AutoLock rlock = new AutoLock(rwLock.readLock())) {
			return dynamicDataSource.getDataSource(lookupKeyCache.get(tenant));
		} 
	}
	
	@Override
	public DefaultDataSource getDefaultDataSource() {
		return dynamicDataSource.getDefaultDataSource();
	}
	
	@Override
	public void supendDataSource() {
	    Stack<Integer> stack = supendedTenant.get();
	    if (stack == null) {
	        stack = new Stack<Integer>();
	        supendedTenant.set(stack);
	    }
	    stack.push(DynamicDataSourceContext.getCurrentTenant());
	}
	
	@Override
	public void resumeDataSource() {
	    Stack<Integer> stack = supendedTenant.get();
		routeDataSource(stack.pop());
	}

	@Override
	public int insertDataSource(DataSourceDO ds) {
		try (AutoResumeFrom resumer = new AutoResumeFrom(this)) {
			if (dataSourceDAO.insertSelective(ds) == 1) {
				dynamicDataSource.addDataSource(ds);
				try (AutoLock wlock = new AutoLock(rwLock.writeLock())) {
					lookupKeyCache.put(ds.getTenant(), ds.getUrl());
				}
				return 1;
			}
			return 0;
		} 
	}

	@Override
	public DataSourceDO selectDataSourceByTenant(Integer tenantId) {
		return dataSourceDAO.selectByTenant(tenantId);
	}

	@Override
	public DataSourceDO selectDataSourceByDomain(String domain) {
		return dataSourceDAO.selectByDomain(domain);
	}

	@Override
	public int updateDataSourceByTenant(DataSourceDO ds) {
		if(dataSourceDAO.updateByTenant(ds) == 1) {
			try (AutoLock wlock = new AutoLock(rwLock.writeLock())) {
				String oldUrl = lookupKeyCache.get(ds.getTenant());
				if (oldUrl == null) {
					dynamicDataSource.addDataSource(ds);
				} else if (!oldUrl.equals(ds.getUrl())) {
					checkDeleteDynamicDataSource(ds.getTenant());
					dynamicDataSource.addDataSource(ds);
				}
				lookupKeyCache.put(ds.getTenant(), ds.getUrl());
			}
			return 1;
		}
		return 0;
	}

	@Override
	public int updateDataSourceByDomain(DataSourceDO ds) {
		DataSourceBaseDO dsOld = dataSourceDAO.selectBaseByDomain(ds.getDomain());
		if (dataSourceDAO.updateByDomainSelective(ds) == 1) {
			try (AutoLock wlock = new AutoLock(rwLock.writeLock())) {
				String oldUrl = lookupKeyCache.get(dsOld.getTenant());
				if (oldUrl == null) {
					dynamicDataSource.addDataSource(ds);
				} else if (dsOld.getTenant() != ds.getTenant() || !oldUrl.equals(ds.getUrl())) {
					checkDeleteDynamicDataSource(dsOld.getTenant());
					dynamicDataSource.addDataSource(ds);
				}
				lookupKeyCache.put(ds.getTenant(), ds.getUrl());
			}
			return 1;
		}
		return 0;
	}

	@Override
	public int deleteDataSource(int tenant) {
		try (AutoResumeFrom resumer = new AutoResumeFrom(this)) {
			if (dataSourceDAO.deleteByTenant(tenant) == 1) {
				try (AutoLock wlock = new AutoLock(rwLock.writeLock())) {
					checkDeleteDynamicDataSource(tenant);
				}
				return 1;
			}
			return 0;
		}
	}

	private void checkDeleteDynamicDataSource(int tenant) {
		String url = lookupKeyCache.remove(tenant);
		Optional<? extends Object> more = lookupKeyCache.entrySet().parallelStream()
			.filter(kv->kv.getValue().equals(url))
			.findAny();
		if (!more.isPresent()) {
			dynamicDataSource.delDataSource(url);
		}
	}
}
