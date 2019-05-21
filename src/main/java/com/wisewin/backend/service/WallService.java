package com.wisewin.backend.service;

import com.wisewin.backend.dao.WallDAO;
import com.wisewin.backend.entity.bo.WallBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by 王彬 on 2019/5/21.
 */
@Service("wallService")
@Transactional
public class WallService {

    @Resource
    private WallDAO wallDAO;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public WallBO queryWallById(String id){
        return wallDAO.queryWallById(id);
    }

    /**
     * 修改
     * @param wall
     */
    public void updateWall(WallBO wall){
        wall.setUpdateTime(new Date());
        wallDAO.updateWall(wall);
    }


    /**
     * 添加
     * @param wall
     */
    public void insertWall(WallBO wall){
        wall.setCreateTime(new Date());
        wall.setUpdateTime(new Date());
        wallDAO.insertWall(wall);
    }
}
