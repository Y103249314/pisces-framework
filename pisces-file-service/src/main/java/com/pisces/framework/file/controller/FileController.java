/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.file.controller;

import com.pisces.framework.core.context.PiscesContext;
import com.pisces.framework.core.guid.Guid;
import com.pisces.framework.core.util.DateWarpUtils;
import com.pisces.framework.file.pojo.FileRespVO;
import com.pisces.framework.file.pojo.FileVO;
import com.pisces.framework.file.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;

@Controller
public class FileController {
	private static final Logger log = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private FileService fileService;

	@RequestMapping("/file/upload")
	@ResponseBody
	public FileRespVO uploadFile(MultipartFile file) throws IOException {
		
		String fileName = file.getOriginalFilename();
		int start = StringUtils.lastIndexOf(fileName, ".");
		String ext = StringUtils.substring(fileName, start);
		
		FileVO fileVO = new FileVO();
		Date currentDate = new Date();
		String tenantId = PiscesContext.getCurrentContext().getTenantId();
		String id = Guid.generateId();
		
		fileVO.setTenantId(tenantId);
		fileVO.setId(id);
		fileVO.setCreateBy(PiscesContext.getCurrentContext().getLoginUserId());
		fileVO.setCreateDate(currentDate);
		fileVO.setFileName(fileName);
		fileVO.setIsDeleted(false);
		fileVO.setTenantId(tenantId);
		fileVO.setUpdateBy(PiscesContext.getCurrentContext().getLoginUserId());
		fileVO.setUpdateDate(currentDate);
		fileVO.setMemo(ext);// 保存后缀名
		fileVO.setFileSize((long) (file.getSize() / 1024));
		fileVO.setUrl(fileService.upload(file.getBytes(), ext)); // 保存文件

		// 保存到数据库
		fileService.insertFile(fileVO);
		FileRespVO resp = new FileRespVO();
		resp.setFileName(fileVO.getFileName());
		resp.setFileId(fileVO.getId());
		resp.setFilePath(fileVO.getUrl());
		resp.setFileSize(fileVO.getFileSize());
		resp.setMemo(fileVO.getMemo());
		resp.setCreateDate(DateWarpUtils.format(new Date(), DateWarpUtils.YYYYMMDD));
		resp.setCreateUserId(PiscesContext.getCurrentContext().getLoginUserId());
		resp.setResultCode("00");
		resp.setResultDesc("成功");
		return resp;
	}

	/**
	 * 下载文件.
	 */
	@RequestMapping("/file/downFile")
	public void downloadFile(String path,HttpServletRequest request, HttpServletResponse response, 
			String fileName) throws IOException {
		setFileNameHeader(request, response, fileName);
		download(path, response);
	}
	
	/**
	 * 下载pdf文件.
	 */
	@RequestMapping("/file/downpdf")
	public void downpdf(String path,HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		response.setContentType("application/pdf;charset=UTF-8");
		download(path, response);
	}

	/**
	 * 下载图片.
	 */
	@RequestMapping("/file/downImage")
	public void downloadImage(String path, HttpServletResponse response) throws IOException {
		download(path, response);
	}

	/**
	 * 下载模板文件
	 */
	@RequestMapping("/template/download")
	public void downloadTmeplate(String url, HttpServletResponse response) throws IOException {
		String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()
				.concat("static/custom/template/")
				.concat(url);
		log.info(path);
		
		File file = new File(path);
		if (file.exists()) {
			String fileName = URLEncoder.encode(file.getName(), "UTF-8");
			response.setHeader("Content-Type", "application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setContentType("application/octet-stream");
			response.setCharacterEncoding("UTF-8");
			
			int count = 0;
			byte[] buffer = new byte[1024];
			try (FileInputStream in = new FileInputStream(file); 
					OutputStream out = response.getOutputStream()) {
				while ((count = in.read(buffer, 0, buffer.length)) != -1) {
					out.write(buffer, 0, count);
				}
			} 
		} 
	}
	
	private void setFileNameHeader(HttpServletRequest request, HttpServletResponse response, String fileName)
			throws UnsupportedEncodingException {
		if(StringUtils.isNotBlank(fileName)){
			String downloadFileName = null;
			String userAgent = request.getHeader("user-agent").toLowerCase();  
			if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
				downloadFileName = URLEncoder.encode(fileName, "UTF-8");  
			} else {  
				downloadFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");  
			}
			response.setHeader("Content-Disposition", "attachment;filename=" + downloadFileName);
		}
	}

	private void download(String path, HttpServletResponse response) throws IOException {
		byte[] data = fileService.getFileData(path);
		if (data != null && data.length > 0) {
			try (OutputStream out = response.getOutputStream()) {
				out.write(data, 0, data.length);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
