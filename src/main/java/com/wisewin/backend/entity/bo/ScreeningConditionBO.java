package com.wisewin.backend.entity.bo;

public class ScreeningConditionBO {
    private Integer id; //筛选条件
    private Integer userId; //用户id
    private Integer queryGender; //查询性别
    private Integer startAge; //开始年龄
    private Integer endAge; //结束年龄


    @Override
    public String toString() {
        return "ScreeningConditionBO{" +
                "id=" + id +
                ", userId=" + userId +
                ", queryGender=" + queryGender +
                ", startAge=" + startAge +
                ", endAge=" + endAge +
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

    public Integer getQueryGender() {
        return queryGender;
    }

    public void setQueryGender(Integer queryGender) {
        this.queryGender = queryGender;
    }

    public Integer getStartAge() {
        return startAge;
    }

    public void setStartAge(Integer startAge) {
        this.startAge = startAge;
    }

    public Integer getEndAge() {
        return endAge;
    }

    public void setEndAge(Integer endAge) {
        this.endAge = endAge;
    }
}
