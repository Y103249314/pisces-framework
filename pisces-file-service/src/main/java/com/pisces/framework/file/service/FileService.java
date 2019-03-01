/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.file.service;

import java.io.IOException;

import com.pisces.framework.file.pojo.FileDO;
import com.pisces.framework.file.pojo.FileVO;

/**
 * Function: 文件 Service.接口类 <br/>
 */
public interface FileService {
	/**
	 * Function: 新增一个文件. <br/>
	 */
	int insertFile(FileVO fileVO);
	
	/**
	 * 根据ID查询文件信息
	 */
	public FileDO findById(String id);
	
	/**
	 * 上传文件
	 */
	String upload(byte[] data, String ext) throws IOException;
	
	/**
	 * 获取文件内容
	 */
	byte[] getFileData(String path) throws IOException;
}
