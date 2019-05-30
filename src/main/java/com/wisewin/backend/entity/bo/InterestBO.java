package com.wisewin.backend.entity.bo;

import java.util.Date;

/**
 * 兴趣
 */
public class InterestBO {

    private Integer id; //兴趣id
    private Integer interesttypeId; //兴趣分类id
    private String interestName; //兴趣名称
    private Date createTime; //创建时间
    private Date updateTime; //修改时间
    private Integer createUserId; //创建人
    private Integer updateUserId; //修改人
    private String grade;//等级

    public InterestBO(){}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInteresttypeId() {
        return interesttypeId;
    }

    public void setInteresttypeId(Integer interesttypeId) {
        this.interesttypeId = interesttypeId;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
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

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
