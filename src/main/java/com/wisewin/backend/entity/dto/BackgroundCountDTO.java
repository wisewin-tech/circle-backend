package com.wisewin.backend.entity.dto;

import java.util.List;

/**
 * @Author: Wang bin
 * @date: Created in 14:50 2019/8/30
 */
public class BackgroundCountDTO {

    private List<UserBackgroundDTO> list;
    private int count;

    public List<UserBackgroundDTO> getList() {
        return list;
    }

    public void setList(List<UserBackgroundDTO> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
