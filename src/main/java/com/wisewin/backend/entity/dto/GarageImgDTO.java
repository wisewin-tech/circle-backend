package com.wisewin.backend.entity.dto;

import java.util.List;

/**
 * @Author: Wang bin
 * @date: Created in 15:40 2019/8/30
 */
public class GarageImgDTO {

    private List<GarageDTO> list;
    private Integer count;

    public List<GarageDTO> getList() {
        return list;
    }

    public void setList(List<GarageDTO> list) {
        this.list = list;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
