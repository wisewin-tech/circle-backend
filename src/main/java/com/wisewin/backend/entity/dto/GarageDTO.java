package com.wisewin.backend.entity.dto;

import java.io.Serializable;

/**
 * @Author: Wang bin
 * @date: Created in 15:34 2019/8/30
 */
public class GarageDTO implements Serializable {
    private Integer id;
    private Integer userId;
    private String plateNumber;
    private String branModel;
    private String headingCode;
    private String certificationPictures;
    private String status;
    private String updateTime;
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getBranModel() {
        return branModel;
    }

    public void setBranModel(String branModel) {
        this.branModel = branModel;
    }

    public String getHeadingCode() {
        return headingCode;
    }

    public void setHeadingCode(String headingCode) {
        this.headingCode = headingCode;
    }

    public String getCertificationPictures() {
        return certificationPictures;
    }

    public void setCertificationPictures(String certificationPictures) {
        this.certificationPictures = certificationPictures;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
