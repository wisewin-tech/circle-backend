package com.wisewin.backend.service;

import com.wisewin.backend.dao.TheGarageDAO;
import com.wisewin.backend.entity.bo.TheGarageBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by 王彬 on 2019/5/20.
 */
@Service("theGarageService")
@Transactional
public class TheGarageService {

    @Resource
    private TheGarageDAO theGarageDAO;

    public TheGarageBO queryTheGarageById(String id){
        return theGarageDAO.queryTheGarageById(id);
    }

    public void updateTheGarage(TheGarageBO TheGarage){
        TheGarage.setUpdateTime(new Date());
        theGarageDAO.updateTheGarage(TheGarage);
    }

    public void insertTheGarage(TheGarageBO theGarage){
        theGarage.setStatus("yes");
        theGarage.setCreateTime(new Date());
        theGarage.setUpdateTime(new Date());
        theGarageDAO.insertTheGarage(theGarage);
    }

}
