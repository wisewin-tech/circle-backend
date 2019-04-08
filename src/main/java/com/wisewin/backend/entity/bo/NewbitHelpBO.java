package com.wisewin.backend.entity.bo;

import java.util.Date;

public class NewbitHelpBO {
    private Integer id; //新手帮助
    private Integer pId; //父id
    private String masterTitle; //主标题
    private String slaveTitle; //副标题
    private Date createTime; //创建时间
    private Date updateTime; //修改时间
    private Integer operatorUserId; //修改人id
    private String operatorUserName; //修改人姓名

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getMasterTitle() {
        return masterTitle;
    }

    public void setMasterTitle(String masterTitle) {
        this.masterTitle = masterTitle;
    }

    public String getSlaveTitle() {
        return slaveTitle;
    }

    public void setSlaveTitle(String slaveTitle) {
        this.slaveTitle = slaveTitle;
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

    public Integer getOperatorUserId() {
        return operatorUserId;
    }

    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    public String getOperatorUserName() {
        return operatorUserName;
    }

    public void setOperatorUserName(String operatorUserName) {
        this.operatorUserName = operatorUserName;
    }
}
