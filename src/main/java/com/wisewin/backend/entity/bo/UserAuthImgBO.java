package com.wisewin.backend.entity.bo;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.Date;

/**
 * Created by 王彬 on 2019/5/16.
 */
public class UserAuthImgBO extends BaseModel {
    //id
    private Integer id;
    //用户id
    private Integer userId;
    //状态
    private String status;
    //认证图片路径
    private String imgUrl;
    //认证示例图片的路径
    private String sampleImgUrl;
    //审核员id
    private Integer adminId;

    private Date createTime;
    private Date updateTime;
    //通知给用户的信息
    private String notice;

    public String getSampleImgUrl() {
        return sampleImgUrl;
    }

    public void setSampleImgUrl(String sampleImgUrl) {
        this.sampleImgUrl = sampleImgUrl;
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

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}
