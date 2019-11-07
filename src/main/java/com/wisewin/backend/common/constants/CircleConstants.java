package com.wisewin.backend.common.constants;

public enum CircleConstants {
    /*未被拉黑*/ NOSHILLDING("no"),
    /*被拉黑*/ SHILLDING("yes"),

    /*机器人*/ ROBOT("no"),
    /*用户*/ NOROBOT("yes"),

    /*未通过认证*/ NO("no"),
    /*已通过认证*/ YES("yes"),
    /*未审核*/  NOT("not"),
    /*审核中*/  AUDIT("audit"),
    /*汽车模式*/ CAR("car"),
    /*异性模式*/ ISOMERISM("isomerism"),
    /*同性模式*/  GAY("gay");

    private CircleConstants(String value) {
        this.value = value;
    }

    private CircleConstants(Integer num) {
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