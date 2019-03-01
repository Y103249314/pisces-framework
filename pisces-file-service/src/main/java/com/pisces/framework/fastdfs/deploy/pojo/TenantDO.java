/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.fastdfs.deploy.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TenantDO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id : 主键
	 */
	private String id;
	/**
	 * tenantId :租户Id
	 */
	private String tenantId;
	/**
	 * name : 租户名字
	 */
	private String name;
	/**
	 * domain : 二级域名
	 */
	private String domain;
	/**
	 * deleted : 删除
	 */
	private Boolean isDeleted;

	/**
	 * createBy : 创建人
	 */
	private String createBy;

	/**
	 * create_date : 创建时间
	 */
	private Date createDate;

	/**
	 * update_by : 更新人
	 */
	private String updateBy;

	/**
	 * update_date : 更新时间
	 */
	private Date updateDate;
	
	/**
	 * address : 租户地址
	 */
	private String address;
	
	/**
	 * authBeginDate : 授权开始日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date authBeginDate;

	/**
	 * authEndDate : 授权结束时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date authEndDate;
	
	/**
	 * status : 状态（1：启动，2：禁用）
	 */
	private Boolean status;
	
	/**
     * 省份
     */
    private String provinceId;
    
    /**
     * 城市
     */
    private String cityId;
    
    /**
     * 客户类型
     */
    private String tenantType;
    
    /**
     * 服务版本
     */
    private String edition;
    
    /**
     * 备注
     */
    private String memo;
    
    /**
     * 销售人员
     */
    private String salesman;
	
    /**
     * 销售人员号码
     */
//    private String salePhone;
    
    /**
     * 是否为正式版，1为正式，0为试用
     */
    private Boolean isOfficial;
    
    /**
     * 客户账号
     */
    private String account;
    /**
     * 同步状态：-1（同步失败）；0（未同步）；1（同步完成）
     */
    private int synStatus;
    /**
     * 同步日志
     */
    private String synLog;
    
    /**
     * 账号数
     */
    private Integer accountNum;
    
    /**
     * logo路径
     */
    private String logoPath;
    
    /**
     * 检索式
     */
    private String searchType;
    
    /**
     * 1、表示有权限变更，0、表示没有权限变更
     */
    private Boolean changePermission;
    
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public String getTenantId() {
		return tenantId;
	}

	public String getName() {
		return name;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public String getCreateBy() {
		return createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getAddress() {
		return address;
	}

	public Date getAuthBeginDate() {
		return authBeginDate;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAuthBeginDate(Date authBeginDate) {
		this.authBeginDate = authBeginDate;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getAuthEndDate() {
		return authEndDate;
	}

	public void setAuthEndDate(Date authEndDate) {
		this.authEndDate = authEndDate;
	}

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTenantType() {
        return tenantType;
    }

    public void setTenantType(String tenantType) {
        this.tenantType = tenantType;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

//    public String getSalePhone() {
//        return salePhone;
//    }
//
//    public void setSalePhone(String salePhone) {
//        this.salePhone = salePhone;
//    }

    public Boolean getIsOfficial() {
        return isOfficial;
    }

    public Integer getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(Integer accountNum) {
        this.accountNum = accountNum;
    }

    public void setIsOfficial(Boolean isOfficial) {
        this.isOfficial = isOfficial;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

	public int getSynStatus() {
		return synStatus;
	}

	public void setSynStatus(int synStatus) {
		this.synStatus = synStatus;
	}

	public String getSynLog() {
		return synLog;
	}

	public void setSynLog(String synLog) {
		this.synLog = synLog;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public Boolean getChangePermission() {
        return changePermission;
    }

    public void setChangePermission(Boolean changePermission) {
        this.changePermission = changePermission;
    }
    
}
