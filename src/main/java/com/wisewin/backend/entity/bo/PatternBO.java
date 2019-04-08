package com.wisewin.backend.entity.bo;

import java.util.Date;

public class PatternBO {
    private Integer id; //模式表
    private String patternName; //模式名称
    private String patternExplain; //模式说明
    private Date createTime; //创建时间
    private Date updateTime; //修改时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }

    public String getPatternExplain() {
        return patternExplain;
    }

    public void setPatternExplain(String patternExplain) {
        this.patternExplain = patternExplain;
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
