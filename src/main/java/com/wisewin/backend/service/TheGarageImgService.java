package com.wisewin.backend.service;

import com.wisewin.backend.dao.TheGarageImgDAO;
import com.wisewin.backend.entity.bo.TheGarageImgBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by 王彬 on 2019/5/20.
 */
@Service("theGarageImgService")
@Transactional
public class TheGarageImgService {

    @Resource
    private TheGarageImgDAO theGarageImgDAO;

    public TheGarageImgBO queryTheGarageImgById(String id){
        return theGarageImgDAO.queryTheGarageImgById(id);
    }

    public void updateTheGarageImg(TheGarageImgBO theGarageImg){
        theGarageImg.setUpdateTime(new Date());
        theGarageImgDAO.updateTheGarageImg(theGarageImg);
    }

    public void insetTheGarageImg(TheGarageImgBO theGarageImg){
        theGarageImg.setUpdateTime(new Date());
        theGarageImg.setCreateTime(new Date());
        theGarageImgDAO.insetTheGarageImg(theGarageImg);
    }

}
