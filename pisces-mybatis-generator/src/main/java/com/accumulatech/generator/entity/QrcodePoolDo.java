package com.accumulatech.generator.entity;

import java.io.Serializable;
import java.util.Date;

public class QrcodePoolDo implements Serializable {
    private Long id;

    private Integer qrcodeId;

    private String qrcodeFile;

    private Byte status;

    private Long userId;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQrcodeId() {
        return qrcodeId;
    }

    public void setQrcodeId(Integer qrcodeId) {
        this.qrcodeId = qrcodeId;
    }

    public String getQrcodeFile() {
        return qrcodeFile;
    }

    public void setQrcodeFile(String qrcodeFile) {
        this.qrcodeFile = qrcodeFile;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}