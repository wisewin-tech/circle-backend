package com.wisewin.backend.common.constants;

public enum TheGarageConstants {


    /*未通过认证*/ UNVERIFIED("unverified"),
    /*已通过认证*/ AUTHENTICATED("authenticated"),
    /*未经过审核*/  UNREVIEWED("unreviewed");



    private TheGarageConstants(String value) {
        this.value = value;
    }

    private TheGarageConstants(Integer num) {
        this.num = num;
    }

    private String value;
    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }




}