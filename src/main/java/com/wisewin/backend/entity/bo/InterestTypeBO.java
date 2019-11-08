package com.wisewin.backend.entity.bo;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.List;

public class InterestTypeBO extends BaseModel {
    private Long id;
    private String typeName;
    private List<InterestBO> interestBOList;

    public List<InterestBO> getInterestBOList() {
        return interestBOList;
    }

    public void setInterestBOList(List<InterestBO> interestBOList) {
        this.interestBOList = interestBOList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
