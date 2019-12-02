package com.wisewin.backend.entity.bo;

import com.wisewin.backend.entity.bo.common.base.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class UserBO extends BaseModel {
    private Long id; //用户
    private String phone; //手机
    private String password;//密码
    private Double longitude;//经度
    private Double latitude;//纬度
    private String certificationStatus;//用户认证状态
    private String carStatus;//汽车认证状态
    private String userStatus;//用户状态
    private String robotStatus;//是否为机器人
    private Date createTime;//注册时间

    private CarIncidentBO carIncidentBO;//事件

    public CarIncidentBO getCarIncidentBO() {
        return carIncidentBO;
    }

    public void setCarIncidentBO(CarIncidentBO carIncidentBO) {
        this.carIncidentBO = carIncidentBO;
    }

    private List<ModelBO> modelBOList;

    public List<ModelBO> getModelBOList() {
        return modelBOList;
    }

    public void setModelBOList(List<ModelBO> modelBOList) {
        this.modelBOList = modelBOList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getCertificationStatus() {
        return certificationStatus;
    }

    public void setCertificationStatus(String certificationStatus) {
        this.certificationStatus = certificationStatus;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getRobotStatus() {
        return robotStatus;
    }

    public void setRobotStatus(String robotStatus) {
        this.robotStatus = robotStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
