package com.wisewin.backend.entity.bo;


import java.util.Date;

/**
 *  key value
 */
public class PairingBO {

    private Integer id; //配对id
    private String  key; //键
    private String value; //值
    private String describe;//描述
    private Date createTime; //创建时间
    private Date update_time; //修改时间
    private Integer createUserId; //创建用户id
    private Integer updateUserId; //修改用户id


    public  PairingBO(){}

    public PairingBO(String key, String value, String describe, Integer createUserId, Integer updateUserId) {
        this.key = key;
        this.value = value;
        this.describe = describe;
        this.createUserId = createUserId;
        this.updateUserId = updateUserId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
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
}
