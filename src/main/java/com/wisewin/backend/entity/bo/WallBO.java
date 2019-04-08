package com.wisewin.backend.entity.bo;

import java.util.Date;

public class WallBO {


    private Integer id; //背景墙
    private Integer userId; //用户id
    private Integer sort; //排序
    private Date createTime; //创建时间
    private Integer status; //0/1

    @Override
    public String toString() {
        return "WallBO{" +
                "id=" + id +
                ", userId=" + userId +
                ", sort=" + sort +
                ", createTime=" + createTime +
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
