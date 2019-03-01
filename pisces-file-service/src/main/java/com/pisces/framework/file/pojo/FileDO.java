/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.file.pojo;

import java.util.Date;

/**
 * Function: 文件DO类. <br/>
 */
public class FileDO {
	/**
	 * id : 主键
	 */
	private String id;
	/**
	 * tenantId：租户id
	 * */
	private String tenantId;
	/**
	 * fileName：文件名
	 * */
	private String fileName;
	/**
	 * url：文件路径
	 * */
	private String url;
	/**
	 * fileSize：文件大小
	 * */
	private Long fileSize;
	/**
	 * memo：备注
	 * */
	private String memo;
	/**
	 * createBy：创建人
	 * */
	private String createBy;
	/**
	 * createDate：创建时间
	 * */
	private Date createDate;
	/**
	 * updateBy：更新人
	 * */
	private String updateBy;
	/**
	 * updateDate：更新时间
	 * */
	private Date updateDate;
	/**
	 * isDeleted：删除标志
	 * */
	private Boolean isDeleted;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
