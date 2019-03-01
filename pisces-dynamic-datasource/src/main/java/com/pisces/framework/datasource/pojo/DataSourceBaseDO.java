/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.datasource.pojo;

import java.io.Serializable;

import com.pisces.framework.datasource.core.DynamicDataSourceDO;

public class DataSourceBaseDO extends DynamicDataSourceDO implements Serializable {
	
    protected Integer tenant;
    protected String  domain;

    private static final long serialVersionUID = 1L;

    public Integer getTenant() {
        return tenant;
    }

    public void setTenant(Integer tenant) {
        this.tenant = tenant;
    }

    public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DataSourceBaseDO other = (DataSourceBaseDO) that;
        return (this.getTenant() == null ? other.getTenant() == null : this.getTenant().equals(other.getTenant()))
            && (this.getDomain() == null ? other.getDomain() == null : this.getDomain().equals(other.getDomain()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTenant() == null) ? 0 : getTenant().hashCode());
        result = prime * result + ((getDomain() == null) ? 0 : getDomain().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tenant=").append(tenant);
        sb.append(", domain=").append(domain);
        sb.append(", url=").append(url);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}