package com.wisewin.backend.entity.bo;

/**
 * 活跃 匹配 注册 数 记录表
 * */
public class StatisticalRecords {
    private Integer id;
    private String activeCount;//活跃数
    private String registrationCount;//注册数
    private String pairingCount;//配对数
    private String createTime;//当天日期

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(String activeCount) {
        this.activeCount = activeCount;
    }

    public String getRegistrationCount() {
        return registrationCount;
    }

    public void setRegistrationCount(String registrationCount) {
        this.registrationCount = registrationCount;
    }

    public String getPairingCount() {
        return pairingCount;
    }

    public void setPairingCount(String pairingCount) {
        this.pairingCount = pairingCount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
