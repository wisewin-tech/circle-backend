package com.wisewin.backend.service;

import com.wisewin.backend.dao.CaseImgDAO;
import com.wisewin.backend.entity.bo.CaseImgBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by 王彬 on 2019/5/16.
 */
@Service("caseImgService")
@Transactional
public class CaseImgService {

    @Resource
    private CaseImgDAO caseImgDao;

    public CaseImgBO queryCaseImg(String id){
        return caseImgDao.queryCaseImg(id);
    }

    public void queryCaseImg(CaseImgBO caseImg){
        caseImg.setCreateTime(new Date());
        caseImg.setUpdateTime(new Date());
        caseImgDao.insertCaseImg(caseImg);
    }

    public void updateCaseImg(CaseImgBO caseImg){
        caseImg.setUpdateTime(new Date());
        caseImgDao.updateCaseImg(caseImg);
    }
}
