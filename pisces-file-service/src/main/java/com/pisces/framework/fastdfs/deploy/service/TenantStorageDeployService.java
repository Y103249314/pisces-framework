/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.fastdfs.deploy.service;

import com.pisces.framework.fastdfs.deploy.pojo.TenantDO;

public interface TenantStorageDeployService {
	public void deployStorage(TenantDO tenantDO);
}
