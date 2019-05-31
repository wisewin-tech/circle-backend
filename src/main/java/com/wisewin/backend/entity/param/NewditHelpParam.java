package com.wisewin.backend.entity.param;

public class NewditHelpParam {
    private String masterTitle;//标题
    private Integer pid;//父ID
    private Integer id;//id
    private Integer operatoruserid;//修改人id
    private String operatorusername;//修改人姓名

    public String getMasterTitle() {
        return masterTitle;
    }

    public void setMasterTitle(String masterTitle) {
        this.masterTitle = masterTitle;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOperatoruserid() {
        return operatoruserid;
    }

    public void setOperatoruserid(Integer operatoruserid) {
        this.operatoruserid = operatoruserid;
    }

    public String getOperatorusername() {
        return operatorusername;
    }

    public void setOperatorusername(String operatorusername) {
        this.operatorusername = operatorusername;
    }


}
