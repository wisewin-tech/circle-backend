package com.wisewin.backend.util.hx.entity;

public class HXUser {
    private String uuid; //用户的UUID，标识字段
    private String type; //类型，“user”用户类型
    private Long created;
    private Long modified;
    private String username; //用户名，也就是环信 ID,（唯一，非空）
    private String nickName; //昵称
    private boolean activated; //用户是否已激活，“true”已激活，“false“封禁，封禁需要通过解禁接口进行解禁，才能正常登录

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
