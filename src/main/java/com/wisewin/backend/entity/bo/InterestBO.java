package com.wisewin.backend.entity.bo;


import java.util.Date;

public class InterestBO {
    private Integer id; //系统兴趣
    private Integer typeId; //系统兴趣分类id
    private String typeName; //系统兴趣分类名称
    private String interestName;// 兴趣
    private Date createTime;//创建时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    @Override
    public String toString() {
        return "DefaultInterestBO{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", interestName='" + interestName + '\'' +
                '}';
    }
}
