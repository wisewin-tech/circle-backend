package com.wisewin.backend.entity.bo;

import java.util.Date;

public class UserBO {
    private Integer id; //用户
    private String phone; //手机
    private String password;
    private String name; //姓名
    private String gender; //性别0女 1男
    private Integer age; //年龄
    private String email; //邮箱
    private Integer stature; //身高(cm)
    private Date birthday; //生日
    private String constellation; //星座
    private String birthplace; //出生地
    private String schooling; //受教育程度
    private String introduce; //介绍
    private Date createTime; //创建时间
    private String work; //工作
    private String school; //学校
    private Integer authenticationStatus; //认证状态
    @Override
    public String toString() {
        return "UserBO{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", stature=" + stature +
                ", birthday='" + birthday + '\'' +
                ", constellation='" + constellation + '\'' +
                ", birthplace='" + birthplace + '\'' +
                ", schooling='" + schooling + '\'' +
                ", introduce='" + introduce + '\'' +
                ", createTime=" + createTime +
                ", work='" + work + '\'' +
                ", school='" + school + '\'' +
                '}';
    }


    public UserBO() {
    }

    public UserBO(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(Integer authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }
}
