package com.wisewin.backend.entity.bo;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.Date;
import java.util.List;

public class UserReportBO extends BaseModel {
    private Long id;//举报
    private Long userId;//举报者id
    private Long userPhone;
    private Long beReportUserId;//被举报者id
    private Long beReportPhone;
    private String status;//状态 yes已读
    private String reason;//原因
    private String describe;//描述
    private Date createTime;//创建时间
    private List<UserReportPicBO> userReportPicBOS;

    public Date getCreateTime() {
        return createTime;
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

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public Long getBeReportUserId() {
        return beReportUserId;
    }

    public void setBeReportUserId(Long beReportUserId) {
        this.beReportUserId = beReportUserId;
    }

    public Long getBeReportPhone() {
        return beReportPhone;
    }

    public void setBeReportPhone(Long beReportPhone) {
        this.beReportPhone = beReportPhone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<UserReportPicBO> getUserReportPicBOS() {
        return userReportPicBOS;
    }

    public void setUserReportPicBOS(List<UserReportPicBO> userReportPicBOS) {
        this.userReportPicBOS = userReportPicBOS;
    }
}
