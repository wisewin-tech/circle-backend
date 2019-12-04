package com.wisewin.backend.util.hx.entity;

public class Token {
    private String access_token; //有效的token字符串
    private String expires_in;   //token 有效时间，以秒为单位，在有效期内不需要重复获取
    private String application;  //当前 App 的 UUID 值

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }
}