package com.wisewin.backend.entity.bo;

import com.wisewin.backend.common.base.BaseModel;

import java.util.Date;

/**
 * 活跃 匹配 注册 数 记录表
 * */
public class StatisticalRecords extends BaseModel {
    private Integer id;
    private Integer activeCount;//活跃数
    private Integer registrationCount;//注册数
    private Integer pairingCount;//配对数
    private Date createTime;//当天日期

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(Integer activeCount) {
        this.activeCount = activeCount;
    }

    public Integer getRegistrationCount() {
        return registrationCount;
    }

    public void setRegistrationCount(Integer registrationCount) {
        this.registrationCount = registrationCount;
    }

    public Integer getPairingCount() {
        return pairingCount;
    }

    public void setPairingCount(Integer pairingCount) {
        this.pairingCount = pairingCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
