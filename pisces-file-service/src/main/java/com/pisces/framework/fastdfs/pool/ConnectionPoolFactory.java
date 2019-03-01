/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.fastdfs.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.csource.fastdfs.StorageClient;

import com.pisces.framework.fastdfs.core.FastDFSTemplateFactory;

/**
 * FastDFS 连接池工厂
 */
public class ConnectionPoolFactory {

    private GenericObjectPool<StorageClient> pool;


    public ConnectionPoolFactory(FastDFSTemplateFactory fastDFSTemplateFactory) {
        pool = new GenericObjectPool<>(new ConnectionFactory(fastDFSTemplateFactory));
    }

    public StorageClient getClient() throws Exception {
        return pool.borrowObject();
    }

    public void releaseConnection(StorageClient client) {
        try {
            pool.returnObject(client);
        } catch (Exception ignored) {
        }
    }


    private void toConfig(PoolConfig poolConfig) {
        pool.setMaxTotal(poolConfig.maxTotal);
        pool.setMaxIdle(poolConfig.maxIdle);
        pool.setMinIdle(poolConfig.minIdle);
        pool.setTestOnBorrow(poolConfig.testOnBorrow);
        pool.setMaxWaitMillis(poolConfig.maxWait);
    }

}