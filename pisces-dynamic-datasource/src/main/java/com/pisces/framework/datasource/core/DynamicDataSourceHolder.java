/**
 * Copyright (c) 2017 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.datasource.core;

/**
 * 设置当前线程数据源的lookupKey，供DynamicDataSource使用
 * @author yangxh
 * @date 2018年8月10日 下午5:12:25
 */
public class DynamicDataSourceHolder {
    private static final ThreadLocal<String> localDataSource = new ThreadLocal<>();

    public static void setDataSource(String dbUrl) {
    	localDataSource.set(dbUrl);
    }

    public static String getDataSource() {
        return (String) localDataSource.get();
    }

    public static void clearDataSource() {
    	localDataSource.remove();
    }
}
