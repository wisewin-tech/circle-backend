package com.wisewin.backend.entity.bo;

import java.util.Date;

public class DefaultInterestLabelBO {
    private Integer id; //默认兴趣标签
    private Integer defaultInterestId; //默认兴趣标签主键
    private String label; //内容
    private Date createTime; //创建时间


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDefaultInterestId() {
        return defaultInterestId;
    }

    public void setDefaultInterestId(Integer defaultInterestId) {
        this.defaultInterestId = defaultInterestId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
