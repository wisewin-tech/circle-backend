package com.wisewin.backend.entity.dto;

import com.wisewin.backend.entity.bo.UserBackgroundBO;

import java.util.List;

/**
 * @Author: Bin Wang
 * @date: Created in 11:19 2019/6/5
 */
public class UserBackBTO {
    private Integer count;
    private List<UserBackgroundBO> list;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<UserBackgroundBO> getList() {
        return list;
    }

    public void setList(List<UserBackgroundBO> list) {
        this.list = list;
    }
}
