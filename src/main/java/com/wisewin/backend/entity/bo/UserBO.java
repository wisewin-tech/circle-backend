package com.wisewin.backend.entity.bo;

import com.wisewin.backend.entity.bo.common.base.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserBO extends BaseModel {
    private Integer id; //用户
    private String phoneNumber; //手机
    private String password;
    private String name; //姓名
    private String gender; //性别 帅哥|美女
    private String email; //邮箱
    private Integer stature; //身高(cm)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday; //生日
    private String constellation; //星座
    private String birthplace; //出生地
    private String createTime;//创建时间
    private String schooling; //受教育程度
    private String work; //工作
    private String school; //学校
    private String authenticationStatus; //认证状态
    private Date authenticationTime;//认证时间
    private Integer updateCount; //修改次数
    private String longitude;//经度
    private String latitude;//纬度
    private String accountStatus;//账号状态

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Date getAuthenticationTime() {
        return authenticationTime;
    }

    public void setAuthenticationTime(Date authenticationTime) {
        this.authenticationTime = authenticationTime;
    }

    public UserBO() {
    }
    public UserBO(Integer id, String phoneNumber, String password, String name) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.name = name;
    }
    /**
     * 注册初始化
     * @param phoneNumber
     */
    public UserBO(String phoneNumber){
        this.phoneNumber=phoneNumber;
        this.updateCount=0;
        this.accountStatus="yes";
    }

    public UserBO(String phone, String password) {
        this.phoneNumber = phone;
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(Integer updateCount) {
        this.updateCount = updateCount;
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

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStature() {
        return stature;
    }

    public void setStature(Integer stature) {
        this.stature = stature;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getConstellation() {
        return constellation;
    }


    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getSchooling() {
        return schooling;
    }

    public void setSchooling(String schooling) {
        this.schooling = schooling;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }


    public String getAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(String authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }


}
