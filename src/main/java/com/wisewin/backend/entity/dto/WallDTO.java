package com.wisewin.backend.entity.dto;

import com.wisewin.backend.entity.bo.WallBO;
import com.wisewin.backend.web.controller.base.BaseCotroller;

import java.util.List;

/**
 * @Author: Bin Wang
 * @date: Created in 11:09 2019/6/4
 */
public class WallDTO extends BaseCotroller {
    //条数
    private Integer count;
    //集合
    private List<WallBO> wallBOS;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<WallBO> getWallBOS() {
        return wallBOS;
    }

    public void setWallBOS(List<WallBO> wallBOS) {
        this.wallBOS = wallBOS;
    }
}
