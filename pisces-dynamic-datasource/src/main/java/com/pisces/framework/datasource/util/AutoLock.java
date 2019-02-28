/**
 * Copyright (c) 2017 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.datasource.util;

import java.util.concurrent.locks.Lock;

/**
 * @author yangxh
 * @date 2018年8月14日 下午4:32:53
 */
public class AutoLock implements AutoCloseable {
	
	private Lock lock;
	
	public AutoLock(Lock lock) {
		this.lock = lock;
		lock.lock();
	}

	@Override
	public void close() {
		this.lock.unlock();
	}
}
