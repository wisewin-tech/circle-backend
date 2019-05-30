package com.wisewin.backend.entity.bo;

public class InterestLabelBO {
    private Integer id; //兴趣标签
    private Integer interestId; //兴趣名称主键
    private String label; //标签内容

    @Override
    public String toString() {
        return "InterestLabelBO{" +
                "id=" + id +
                ", interestId=" + interestId +
                ", label='" + label + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInterestId() {
        return interestId;
    }

    public void setInterestId(Integer interestId) {
        this.interestId = interestId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
