package com.wisewin.backend.entity.bo;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.Date;

public class CarCertificationBO extends BaseModel {
    private Long id;//车辆认证
    private Long userId;//用户id
    private String phone;//用户手机号
    private String carNumber;//车牌号
    private String carNumberPicture;//车牌号照片
    private String carModels;//车型
    private String carModelsNumber;//车型编号
    private String carPicture;//开门照片
    private String licenseType;//驾照类型
    private String licensePicture;//驾照类型照片
    private String drivingPicture;//行驶证照片
    private String carCertificationStatus;//是否通过
    private Date createTime;
    private Date updateTime;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarNumberPicture() {
        return carNumberPicture;
    }

    public void setCarNumberPicture(String carNumberPicture) {
        this.carNumberPicture = carNumberPicture;
    }

    public String getCarModels() {
        return carModels;
    }

    public void setCarModels(String carModels) {
        this.carModels = carModels;
    }

    public String getCarModelsNumber() {
        return carModelsNumber;
    }

    public void setCarModelsNumber(String carModelsNumber) {
        this.carModelsNumber = carModelsNumber;
    }

    public String getCarPicture() {
        return carPicture;
    }

    public void setCarPicture(String carPicture) {
        this.carPicture = carPicture;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getLicensePicture() {
        return licensePicture;
    }

    public void setLicensePicture(String licensePicture) {
        this.licensePicture = licensePicture;
    }

    public String getDrivingPicture() {
        return drivingPicture;
    }

    public void setDrivingPicture(String drivingPicture) {
        this.drivingPicture = drivingPicture;
    }

    public String getCarCertificationStatus() {
        return carCertificationStatus;
    }

    public void setCarCertificationStatus(String carCertificationStatus) {
        this.carCertificationStatus = carCertificationStatus;
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
