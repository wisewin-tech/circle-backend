package com.wisewin.backend.entity.bo;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.Date;

/**
 * Created by 王彬 on 2019/5/16.
 */
public class TheGarageBO extends BaseModel {
    //id
    private Integer id;
    //用户id
    private Integer userId;
    //车牌号
    private String plateNumber;
    //品牌型号
    private String branModel;
    //车辆识别码
    private String headingCode;
    //认证图片
    private String certificationPictures;
    //认证状态
    private String status;
    //修改时间
    private Date updateTime;
    //创建时间
    private Date createTime;

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

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getBranModel() {
        return branModel;
    }

    public void setBranModel(String branModel) {
        this.branModel = branModel;
    }

    public String getHeadingCode() {
        return headingCode;
    }

    public void setHeadingCode(String headingCode) {
        this.headingCode = headingCode;
    }

    public String getCertificationPictures() {
        return certificationPictures;
    }

    public void setCertificationPictures(String certificationPictures) {
        this.certificationPictures = certificationPictures;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
