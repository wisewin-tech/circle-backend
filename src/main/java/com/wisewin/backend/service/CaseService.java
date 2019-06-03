package com.wisewin.backend.service;

import com.wisewin.backend.common.constants.CaseConstants;
import com.wisewin.backend.dao.CaseDAO;
import com.wisewin.backend.entity.bo.CaseBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 认证案例管理
 * */
@Service("CaseService")
@Transactional
public class CaseService {

    @Resource
    CaseDAO caseDAO;

    public boolean addCase(CaseBO caseImgBO){
        caseImgBO.setStatus(CaseConstants.YES.getValue());
        return caseDAO.addCase(caseImgBO)>0;
    }

    public boolean updCase(CaseBO caseImgBO){
        if(caseImgBO.getStatus()!=null&&!caseImgBO.getStatus().equals("")){
            if(caseImgBO.getStatus().equals("yes")){
                caseImgBO.setStatus(CaseConstants.YES.getValue());
            }else{
                caseImgBO.setStatus(CaseConstants.NO.getValue());
            }
        }
        return caseDAO.updCase(caseImgBO)>0;
    }

    public List<CaseBO> getCaseImg(){
        return caseDAO.getCaseImg();
    }

}
