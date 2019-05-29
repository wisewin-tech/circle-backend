package com.wisewin.backend.entity.param;

public class NewditHelpParam {
    private String masterTitle;//标题
    private Integer pId;//父ID
    private Integer id;//id


    public String getMasterTitle() {
        return masterTitle;
    }

    public void setMasterTitle(String masterTitle) {
        this.masterTitle = masterTitle;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
