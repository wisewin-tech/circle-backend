package com.wisewin.backend.entity.bo;

import java.util.Date;

/**
 * 背景墙
 */
public class BackgroundBO {

    private Integer id; //Date模式id
    private Integer userId;//用户id
    private Date createTime;// 创建时间
    private Date updateTime; //修改时间
    private String nameurl; //图片地址
    private Double  rank; //排序
    private Integer pattern;//模式id
    private String state; //模式
    private String stateone; //模式1


    public BackgroundBO(String nameurl, Double rank, Integer pattern) {
        this.nameurl = nameurl;
        this.rank = rank;
        this.pattern = pattern;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateone() {
        return stateone;
    }

    public void setStateone(String stateone) {
        this.stateone = stateone;
    }

    public BackgroundBO(){}

    public Integer getPattern() {
        return pattern;
    }

    public void setPattern(Integer pattern) {
        this.pattern = pattern;
    }

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

    public String getNameurl() {
        return nameurl;
    }

    public void setNameurl(String nameurl) {
        this.nameurl = nameurl;
    }

    public Double getRank() {
        return rank;
    }

    public void setRank(Double rank) {
        this.rank = rank;
    }
}
