/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.file.pojo;

/**
 * Function: 文件返回结果 VO类. <br/>
 */
public class FileRespVO {
	/**
	 * fileName：文件名
	 * */
	private String fileName;
	
	/**
	 * filePath：文件保存的相对路径
	 * */
	private String filePath;
	
	/**
	 * fileId：文件ID
	 * */
	private String fileId;
	
	/**
	 * url：文件路径
	 * */
	private String url;
	
	/**
	 * fileSize：文件大小
	 * */
	private Long fileSize;

	private String createDate;
	
	private String createUser;
	
	private String createUserId;
	
	/**
	 * memo：备注
	 * */
	private String memo;

	/** resultCode：返回代码 00成表成功，其它代表失败 */
	private String resultCode;
	
	/** resultDesc：返回描述 */
	private String resultDesc;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCreateDate() {
		return createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

}
