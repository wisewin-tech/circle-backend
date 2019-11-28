package com.wisewin.backend.entity.bo;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.List;

public class ChinaRegionBO extends BaseModel {
    private Long id;
    private String name;
    private Long pid;
    private List<ChinaRegionBO2>chinaRegionBOList;

    public List<ChinaRegionBO2> getChinaRegionBOList() {
        return chinaRegionBOList;
    }

    public void setChinaRegionBOList(List<ChinaRegionBO2> chinaRegionBOList) {
        this.chinaRegionBOList = chinaRegionBOList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}
