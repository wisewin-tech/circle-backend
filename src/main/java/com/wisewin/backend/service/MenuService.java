package com.wisewin.backend.service;

import com.wisewin.backend.dao.MenuDAO;
import com.wisewin.backend.entity.bo.MenuBO;
import com.wisewin.backend.util.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by 王彬 on 2019/5/16.
 */
@Service
@Transactional
public class MenuService {

    @Resource
    private MenuDAO menuDAO;

    public MenuBO queryMenu(String id){
        return menuDAO.queryMenu(id);
    }

    public void insertMenu(MenuBO menu){
        menu.setCreateTime(DateUtils.now());
        menu.setUpdateTime(DateUtils.now());
        menuDAO.insertMenu(menu);
    }

    public void updateMenu(MenuBO menu){
        menu.setUpdateTime(DateUtils.now());
        menuDAO.updateMenu(menu);
    }
}
