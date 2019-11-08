package com.wisewin.backend.entity.bo;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.Date;

public class UserCertificationBO extends BaseModel {
    private Long id;//用户认证
    private Long userId;//用户id
    private Long demoId;//案例id
    private String demoPicture;//认证图片
    private String userPicture;//用户认证图片
    private String status;//状态
    private String remark;//备注
    private Date createTime;
    private Date updateTime;
    private Integer adminId;//操作员id
    private String adminName;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getDemoPicture() {
        return demoPicture;
    }

    public void setDemoPicture(String demoPicture) {
        this.demoPicture = demoPicture;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
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

    public Long getDemoId() {
        return demoId;
    }

    public void setDemoId(Long demoId) {
        this.demoId = demoId;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
