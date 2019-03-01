/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.file.dao;

import java.util.Map;

import com.pisces.framework.file.pojo.FileDO;

/**
 * 对应表 ip_file 的基本DAO
 */
@SuppressWarnings("rawtypes")
public interface FileDAO {
	/**
	 * Function: 新增一个文件. <br/>
	 */
	int insertFile(FileDO fileDO);
	
	/**
	 * Function: 删除一个文件. <br/>
	 */	
	int deleteFile(Map map);
	
	/**
	 * Function:批量删除一个文件. <br/>
	 */
	int deleteFileByBath(Map map);
	
	/**
	 * 根据ID查询
	 */
	public FileDO findById(String id);

}
