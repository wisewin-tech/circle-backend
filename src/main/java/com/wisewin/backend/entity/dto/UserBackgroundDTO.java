package com.wisewin.backend.entity.dto;

import java.util.Date;

/**
 * @Author: Wang bin
 * @date: Created in 13:39 2019/8/30
 */
public class UserBackgroundDTO {
    //id
    private Integer id;
    //图片地址
    private String nameurl;
    //用户id
    private Integer userId;
    //添加时间
    private String createTime;
    //修改时间
    private String updateTime;
    //排序
    private Integer  rank;
    //模式id
    private Integer pattern;
    //状态
    private String state;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameurl() {
        return nameurl;
    }

    public void setNameurl(String nameurl) {
        this.nameurl = nameurl;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getPattern() {
        return pattern;
    }

    public void setPattern(Integer pattern) {
        this.pattern = pattern;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
