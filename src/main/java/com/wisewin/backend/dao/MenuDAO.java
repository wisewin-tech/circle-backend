package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.MenuBO;

/**
 * Created by 王彬 on 2019/5/16.
 */
public interface MenuDAO {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    MenuBO queryMenu(String id);

    /**
     * 添加
     * @param menu
     */
    void insertMenu(MenuBO menu);

    /**
     * 修改
     * @param menu
     */
    void updateMenu(MenuBO menu);
}
