package com.wisewin.backend.entity.bo;

import com.wisewin.backend.common.base.BaseModel;

import java.util.Date;

public class MatchingFriendEdBO extends BaseModel {
    private Long id;//好友关系表
    private Long userId;//用户id
    private Long friendsId;//朋友id
    private String model;//模式
    private String friendsStatus;//状态
    private Date createTime;//创建时间
    private Date closeTime;//结束时间
    private String remark;//备注

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

    public Long getFriendsId() {
        return friendsId;
    }

    public void setFriendsId(Long friendsId) {
        this.friendsId = friendsId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFriendsStatus() {
        return friendsStatus;
    }

    public void setFriendsStatus(String friendsStatus) {
        this.friendsStatus = friendsStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
