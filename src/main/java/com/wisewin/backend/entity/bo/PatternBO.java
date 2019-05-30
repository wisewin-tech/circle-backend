package com.wisewin.backend.entity.bo;


import java.util.Date;

//用户模式
public class PatternBO {

    private Integer id;  //用户模式
    private Integer userId; //用户id
    private Date updateTime; //最后修改时间
    private String describe; //描述
    private String interest; //兴起
    private String  type; //模式  DATE/BFF模式


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
