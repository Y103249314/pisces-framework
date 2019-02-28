/**
 * Copyright (c) 2017 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.datasource.core;

public class DynamicDataSourceContext {
    private final static ThreadLocal<Integer> currentTenant = new ThreadLocal<Integer>();
    
    public static void setCurrentTenant(Integer tenantId) {
    	currentTenant.set(tenantId);
    }
    
    public static Integer getCurrentTenant() {
    	return currentTenant.get();
    }
    
    public static void removeCurrentTenant() {
    	currentTenant.remove();
    }
}
