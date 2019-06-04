package com.wisewin.backend.entity.bo;


import java.util.Date;

//用户模式
public class PatternBO {

    private Integer id;  //用户模式
    private Integer userId; //用户id
    private Date updateTime; //最后修改时间
    private String describe; //描述
    private String interest; //兴趣
    private String  type; //模式  DATE/BFF模式

    private Integer inquireAge;//查询年龄开始
    private Integer inquireSex;//查询性别
    private Integer inquireAgeOver;//查询年龄结束
    private String queryLocation;//查询位置
    private Integer likeSum;//被喜欢次数

    public Integer getInquireAge() {
        return inquireAge;
    }

    public void setInquireAge(Integer inquireAge) {
        this.inquireAge = inquireAge;
    }

    public Integer getInquireSex() {
        return inquireSex;
    }

    public void setInquireSex(Integer inquireSex) {
        this.inquireSex = inquireSex;
    }

    public Integer getInquireAgeOver() {
        return inquireAgeOver;
    }

    public void setInquireAgeOver(Integer inquireAgeOver) {
        this.inquireAgeOver = inquireAgeOver;
    }

    public String getQueryLocation() {
        return queryLocation;
    }

    public void setQueryLocation(String queryLocation) {
        this.queryLocation = queryLocation;
    }

    public Integer getLikeSum() {
        return likeSum;
    }

    public void setLikeSum(Integer likeSum) {
        this.likeSum = likeSum;
    }

    public PatternBO(Integer userId, String type) {
        this.userId = userId;
        this.type = type;
    }

    public PatternBO(){}

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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
