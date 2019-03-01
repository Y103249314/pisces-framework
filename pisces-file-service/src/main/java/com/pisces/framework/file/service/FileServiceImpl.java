/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.file.service;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pisces.framework.fastdfs.core.FastDFSFile;
import com.pisces.framework.fastdfs.core.FastDFSTemplate;
import com.pisces.framework.file.dao.FileDAO;
import com.pisces.framework.file.pojo.FileDO;
import com.pisces.framework.file.pojo.FileVO;

/**
 * Function: 文件 Service实现类. <br/>
 */
@Service
public class FileServiceImpl implements FileService {
	private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
	
	@Autowired
	private FileDAO fileDAO;
	
	@Autowired
	FastDFSTemplate fastDFSTemplate;

	@Override
	public int insertFile(FileVO fileVO) {
		FileDO fileDO = new FileDO();
		BeanUtils.copyProperties(fileVO, fileDO);
		return fileDAO.insertFile(fileDO);
	}
	
	@Override
	public FileDO findById(String id) {
		return fileDAO.findById(id);
	}
	
	@Override
	public String upload(byte[] data, String ext) throws IOException {
		String filePath = null;
		
		try {
			String ext0 = StringUtils.isBlank(ext) ? "" : ext.substring(1);
			FastDFSFile fdfsFile = fastDFSTemplate.upload(data, ext0);
			if (fdfsFile != null && StringUtils.isNotBlank(fdfsFile.getGroupPath())) {
				filePath = fdfsFile.getGroupPath();
			}
		} catch(Exception e) {
			log.error("文件服务异常", e);
			throw new IOException(e);
		}
		
		return filePath;
	}
	
	@Override
	public byte[] getFileData(String path) throws IOException {
		byte[] data = null;
		
		try {
			data = fastDFSTemplate.download(new FastDFSFile(path));
		} catch (Exception e) {
			log.error("文件服务异常", e.getMessage());
			throw new IOException(e);
		}
		
		return data;
	}

}
