package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.WallBO;

/**
 * Created by 王彬 on 2019/5/20.
 */
public interface WallDAO {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    WallBO queryWallById(String id);

    /**
     * 修改
     * @param wall
     */
    void updateWall(WallBO wall);


    /**
     * 添加
     * @param wall
     */
    void insertWall(WallBO wall);
}
