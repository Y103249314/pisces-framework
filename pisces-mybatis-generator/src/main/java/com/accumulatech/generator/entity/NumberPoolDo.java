package com.accumulatech.generator.entity;

import java.io.Serializable;
import java.util.Date;

public class NumberPoolDo implements Serializable {
    private Long id;

    private String number;

    private Byte status;

    private Date rentTime;

    private Integer rentFeeMonth;

    private Integer callFeeMinute;

    private Date callEndTime;

    private Integer callTimes;

    private Integer callMinutes;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getRentTime() {
        return rentTime;
    }

    public void setRentTime(Date rentTime) {
        this.rentTime = rentTime;
    }

    public Integer getRentFeeMonth() {
        return rentFeeMonth;
    }

    public void setRentFeeMonth(Integer rentFeeMonth) {
        this.rentFeeMonth = rentFeeMonth;
    }

    public Integer getCallFeeMinute() {
        return callFeeMinute;
    }

    public void setCallFeeMinute(Integer callFeeMinute) {
        this.callFeeMinute = callFeeMinute;
    }

    public Date getCallEndTime() {
        return callEndTime;
    }

    public void setCallEndTime(Date callEndTime) {
        this.callEndTime = callEndTime;
    }

    public Integer getCallTimes() {
        return callTimes;
    }

    public void setCallTimes(Integer callTimes) {
        this.callTimes = callTimes;
    }

    public Integer getCallMinutes() {
        return callMinutes;
    }

    public void setCallMinutes(Integer callMinutes) {
        this.callMinutes = callMinutes;
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