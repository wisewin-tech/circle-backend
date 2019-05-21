package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.TheGarageBO;

/**
 * Created by 王彬 on 2019/5/20.
 */
public interface TheGarageDAO {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    TheGarageBO  queryTheGarageById(String id);

    /**
     * 修改
     * @param TheGarage
     */
    void updateTheGarage(TheGarageBO TheGarage);

    /**
     * 添加
     * @param theGarage
     */
    void insertTheGarage(TheGarageBO theGarage);
}
