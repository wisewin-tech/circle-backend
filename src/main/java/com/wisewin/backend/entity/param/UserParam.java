package com.wisewin.backend.entity.param;

import com.wisewin.backend.entity.bo.ModelBO;

import java.util.Date;
import java.util.List;

/**
 * user对象参数类
 */
public class UserParam {
    //分页
    private Integer pageSize;
    private Integer pageNo;
    //user信息
    private Long id; //用户
    private String phone; //手机
    private String password;//密码
    private String longitude;//经度
    private String latitude;//纬度
    private String certificationStatus;//用户认证状态
    private String carStatus;//汽车认证状态
    private String userStatus;//用户状态
    private String robotStatus;//是否为机器人
    private Date createTime;//注册时间
    //模式信息
    private List<ModelBO> modelBOList;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
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

    public List<ModelBO> getModelBOList() {
        return modelBOList;
    }

    public void setModelBOList(List<ModelBO> modelBOList) {
        this.modelBOList = modelBOList;
    }
}
