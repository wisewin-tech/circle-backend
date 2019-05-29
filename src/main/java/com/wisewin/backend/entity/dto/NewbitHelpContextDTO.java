package com.wisewin.backend.entity.dto;

import com.wisewin.backend.entity.bo.common.base.BaseModel;


public class NewbitHelpContextDTO extends BaseModel{

    private Integer id;//id
    private Integer helpId;//新手帮助id
    private String context;//内容
    private String imgUrl;//图片路径（备用字段）
    private String author;//作者（备用字段）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHelpId() {
        return helpId;
    }

    public void setHelpId(Integer helpId) {
        this.helpId = helpId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
