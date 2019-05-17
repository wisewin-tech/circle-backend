package com.wisewin.backend.service;

import com.wisewin.backend.dao.MenuDAO;
import com.wisewin.backend.entity.bo.MenuBO;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by 王彬 on 2019/5/16.
 */
public class MenuService {

    @Resource
    private MenuDAO menuDAO;

    public MenuBO queryMenu(String id){
        return menuDAO.queryMenu(id);
    }

    public void insertMenu(MenuBO menu){
        menu.setCreateTime(new Date());
        menu.setUpdateTime(new Date());
        menuDAO.insertMenu(menu);
    }

    public void updateMenu(MenuBO menu){
        menu.setUpdateTime(new Date());
        menuDAO.updateMenu(menu);
    }
}
