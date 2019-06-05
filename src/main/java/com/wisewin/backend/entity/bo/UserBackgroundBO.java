package com.wisewin.backend.entity.bo;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.Date;

/**
 * @Author: Bin Wang
 * @date: Created in 14:52 2019/6/4
 */
public class UserBackgroundBO extends BaseModel {
    //id
    private Integer id;
    //图片地址
    private String nameurl;
    //用户id
    private Integer userId;
    //添加时间
    private Date createTime;
    //修改时间
    private Date updateTime;
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
