package com.wisewin.backend.entity.bo;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModelBO extends BaseModel {
    private Long id;//模式
    private Long userId;
    private String name;//昵称
    private String model;//模式类型
    private String describe;//说点什么
    private String sex;//性别
    private Long sexCount;//性别修改次数
    private Date birthday;//生日
    private String constellation;//星座
    private String height;//身高
    private String weight;//体重
    private String education;//学历教育
    private String birthplace;//出生地
    private String searchDistance;//搜索距离
    private String searchSex;//搜索性别
    private String searchAge;//搜索年龄
    private String sexStatus;//真实性别开关
    private String carCertificationStatus;//汽车认证开关
    private Long beLikeCount;//被喜欢次数
    private Long beSuperLikeCount;//被超级喜欢次数
    private Long superLikeCount;//超级喜欢次数
    private Date superLikeTime;//超级喜欢时间
    private Long beShieldingCount;//被屏蔽次数
    private Date updateTime;

    private List<UserPictureBO> pictureBOList;//背景图
    private List<InterestTypeBO> interestTypeBOList=new ArrayList<InterestTypeBO>();//兴趣分类
    private List<UserInterestBO> userInterestBOS=new ArrayList<UserInterestBO>();//用户兴趣

    public List<UserInterestBO> getUserInterestBOS() {
        return userInterestBOS;
    }

    public void setUserInterestBOS(List<UserInterestBO> userInterestBOS) {
        this.userInterestBOS = userInterestBOS;
    }

    public List<InterestTypeBO> getInterestTypeBOList() {
        return interestTypeBOList;
    }

    public void setInterestTypeBOList(List<InterestTypeBO> interestTypeBOList) {
        this.interestTypeBOList = interestTypeBOList;
    }

    public List<UserPictureBO> getPictureBOList() {
        return pictureBOList;
    }

    public void setPictureBOList(List<UserPictureBO> pictureBOList) {
        this.pictureBOList = pictureBOList;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getSexCount() {
        return sexCount;
    }

    public void setSexCount(Long sexCount) {
        this.sexCount = sexCount;
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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getSearchDistance() {
        return searchDistance;
    }

    public void setSearchDistance(String searchDistance) {
        this.searchDistance = searchDistance;
    }

    public String getSearchSex() {
        return searchSex;
    }

    public void setSearchSex(String searchSex) {
        this.searchSex = searchSex;
    }

    public String getSearchAge() {
        return searchAge;
    }

    public void setSearchAge(String searchAge) {
        this.searchAge = searchAge;
    }

    public String getSexStatus() {
        return sexStatus;
    }

    public void setSexStatus(String sexStatus) {
        this.sexStatus = sexStatus;
    }

    public String getCarCertificationStatus() {
        return carCertificationStatus;
    }

    public void setCarCertificationStatus(String carCertificationStatus) {
        this.carCertificationStatus = carCertificationStatus;
    }

    public Long getBeLikeCount() {
        return beLikeCount;
    }

    public void setBeLikeCount(Long beLikeCount) {
        this.beLikeCount = beLikeCount;
    }

    public Long getBeSuperLikeCount() {
        return beSuperLikeCount;
    }

    public void setBeSuperLikeCount(Long beSuperLikeCount) {
        this.beSuperLikeCount = beSuperLikeCount;
    }

    public Long getSuperLikeCount() {
        return superLikeCount;
    }

    public void setSuperLikeCount(Long superLikeCount) {
        this.superLikeCount = superLikeCount;
    }

    public Date getSuperLikeTime() {
        return superLikeTime;
    }

    public void setSuperLikeTime(Date superLikeTime) {
        this.superLikeTime = superLikeTime;
    }

    public Long getBeShieldingCount() {
        return beShieldingCount;
    }

    public void setBeShieldingCount(Long beShieldingCount) {
        this.beShieldingCount = beShieldingCount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
