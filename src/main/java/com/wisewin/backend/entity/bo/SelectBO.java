package com.wisewin.backend.entity.bo;

import com.wisewin.backend.entity.bo.common.base.BaseModel;

import java.util.List;

public class SelectBO extends BaseModel {
    private String label;//分类
    private List<OptionsBO> options;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<OptionsBO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionsBO> options) {
        this.options = options;
    }
}
