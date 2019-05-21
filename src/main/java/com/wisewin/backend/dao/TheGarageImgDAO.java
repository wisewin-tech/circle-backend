package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.TheGarageImgBO;

/**
 * Created by 王彬 on 2019/5/20.
 */
public interface TheGarageImgDAO {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    TheGarageImgBO queryTheGarageImgById(String id);

    /**
     * 修改
     * @param theGarageImg
     */
    void updateTheGarageImg(TheGarageImgBO theGarageImg);

    /**
     * 添加
     * @param theGarageImg
     */
    void insetTheGarageImg(TheGarageImgBO theGarageImg);

}
