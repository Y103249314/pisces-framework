/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.core.guid;

/**
 * Function: 全局唯一变量id. <br/>
 */
public class Guid {
	
	/**
	 * Function: 生成id. <br/>
	 */
	public static String generateId(){
		SnowflakeIdWorker idWorker = SnowflakeIdWorker.getIstance();
		long id = idWorker.nextId();
		return String.valueOf(id);
	}
}
