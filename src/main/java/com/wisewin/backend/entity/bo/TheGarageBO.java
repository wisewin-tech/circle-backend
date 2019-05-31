package com.wisewin.backend.entity.bo;

public class TheGarageBO {
    private Integer id; //车库
    private Integer userId; //用户id
    private String plateNumber; //车牌号
    private String brandModel; //品牌型号
    private String certificationPictures; //认证图片
    private Integer status; //认证状态

    @Override
    public String toString() {
        return "TheGarageBO{" +
                "id=" + id +
                ", userId=" + userId +
                ", plateNumber='" + plateNumber + '\'' +
                ", brandModel='" + brandModel + '\'' +
                ", certificationPictures='" + certificationPictures + '\'' +
                ", status=" + status +
                '}';
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

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getBrandModel() {
        return brandModel;
    }

    public void setBrandModel(String brandModel) {
        this.brandModel = brandModel;
    }

    public String getCertificationPictures() {
        return certificationPictures;
    }

    public void setCertificationPictures(String certificationPictures) {
        this.certificationPictures = certificationPictures;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
