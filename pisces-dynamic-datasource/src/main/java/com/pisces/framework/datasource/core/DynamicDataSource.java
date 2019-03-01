/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.datasource.core;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.pisces.framework.datasource.util.AutoLock;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 动态数据源封装。注意：数据源切换要在Transcantional注解之前完成，事务中不支持数据源切换
 * @author yangxh
 * @date 2018年8月10日 上午11:40:46
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	private DefaultDataSource defaultDataSource;
	private ConcurrentHashMap<Object, Object> targetDataSources = new ConcurrentHashMap<Object, Object>();
	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	
	public DynamicDataSource(DefaultDataSource defaultDataSource) {
		this.defaultDataSource = defaultDataSource;
		this.setTargetDataSources(targetDataSources);
		this.setDefaultTargetDataSource(defaultDataSource);
	}
	
    @Override
    protected Object determineCurrentLookupKey() {
    	return DynamicDataSourceHolder.getDataSource();
    }
    
    @Override
    protected DataSource determineTargetDataSource() {
    	try (AutoLock rlock = new AutoLock(rwLock.readLock())) {
    		return super.determineTargetDataSource();
    	}
    }
    
    public void refreshDataSource(List<? extends DynamicDataSourceDO> dsList) {
    	try (AutoLock wlock = new AutoLock(rwLock.writeLock())) {
    		targetDataSources.clear();
	    	addDataSource(dsList);
    	} 
    }
    
    public void addDataSource(DynamicDataSourceDO ds) {
    	try (AutoLock wlock = new AutoLock(rwLock.writeLock())) {
	    	if (!targetDataSources.containsKey(ds.getUrl())) {
	    		addDataSource0(ds);
	            super.setTargetDataSources(targetDataSources);
	            super.afterPropertiesSet();
	    	}
    	}
    }
    
    public void addDataSource(List<? extends DynamicDataSourceDO> dsList) {
    	try (AutoLock wlock = new AutoLock(rwLock.writeLock())) {
    		boolean addFlag = false;
    		for(DynamicDataSourceDO ds: dsList) {
		    	if (!targetDataSources.containsKey(ds.getUrl())) {
		    		addDataSource0(ds);
		    		addFlag = true;
		    	}
    		}
    		if (addFlag) {
	    		super.setTargetDataSources(targetDataSources);
		        super.afterPropertiesSet();
    		}
    	}
    }
    
    
    public DefaultDataSource getDefaultDataSource() {
    	return this.defaultDataSource;
    }
    
    public DefaultDataSource getDataSource(String dbUrl) {
    	try (AutoLock rlock = new AutoLock(rwLock.readLock())) {
    		if (StringUtils.isNotBlank(dbUrl)) {
    			return (DefaultDataSource)targetDataSources.get(dbUrl);
    		} else {
    			return null;
    		}
    	}
    }
    
    public String delDataSource(String dbUrl) {
    	try (AutoLock wlock = new AutoLock(rwLock.writeLock())) {
    		if (targetDataSources.containsKey(dbUrl)) {
    			targetDataSources.remove(dbUrl);
    			super.setTargetDataSources(targetDataSources);
    			super.afterPropertiesSet();
    			return dbUrl;
    		}
    	}
    	return null;
    }
    
    public void delDataSource(List<String> dbUrls) {
    	try (AutoLock wlock = new AutoLock(rwLock.writeLock())) {
    		boolean delFlag = false;
    		for(String url: dbUrls) {
		    	if (targetDataSources.containsKey(url)) {
		    		targetDataSources.remove(url);
		    		delFlag = true;
		    	}
    		}
    		if (delFlag) {
	    		super.setTargetDataSources(targetDataSources);
		        super.afterPropertiesSet();
    		}
    	}
    }
    
    private void addDataSource0(DynamicDataSourceDO ds) {
    	HikariDataSource dataSource = new HikariDataSource();
    	defaultDataSource.copyStateTo(dataSource); // not sealed
        dataSource.setJdbcUrl(replaceUrl(defaultDataSource.getJdbcUrl(), ds.getUrl()));
        dataSource.setUsername(ds.getUsername());
        dataSource.setPassword(ds.getPassword());
        dataSource.setPoolName(getPoolName(ds.getUrl()));
        targetDataSources.put(ds.getUrl(), dataSource);
    }
    
    private String replaceUrl(String srcUrl, String dstUrl) {
    	int paramIndex = srcUrl.indexOf("?");
    	String paramStr = srcUrl.substring(paramIndex);
    	return new StringBuilder(128).append(dstUrl).append(paramStr).toString();
    }
    
    private String getPoolName(String dstUrl) {
    	return new StringBuilder(32)
    			.append(dstUrl.substring(dstUrl.lastIndexOf("_") + 1))
    			.append("HikariCP")
    			.toString();
    }
}
