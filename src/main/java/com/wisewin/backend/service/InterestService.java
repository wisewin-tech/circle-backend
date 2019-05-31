package com.wisewin.backend.service;

import com.wisewin.backend.dao.InterestDAO;
import com.wisewin.backend.dao.InterestSubclassDAO;
import com.wisewin.backend.dao.InterestTypeDAO;
import com.wisewin.backend.entity.bo.InterestBO;
import com.wisewin.backend.entity.bo.InterestSubclassBO;
import com.wisewin.backend.entity.bo.InterestTypeBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/*
* 一级兴趣管理
* */
@Service("InterestService")
@Transactional
public class InterestService {
    @Resource
    InterestDAO interestDAO;

    @Resource
    InterestSubclassDAO interestSubclassDAO;

    //增加一级兴趣
    public boolean addInterest(InterestBO interestBO){
        return interestDAO.addInterest(interestBO)>0;
    }

    /**删除一级兴趣 同时删除二级兴趣
    */
    public boolean delInterest(Integer id){
        //删除子兴趣
        //interestSubclassDAO.delInterestSubclass(null,id);
        //删除兴趣
        return interestDAO.delInterest(id,null)>0;
    }

    //查询一级兴趣
    public List<InterestBO> getInterestsByTypeId(Integer interestTypeId){
        return interestDAO.getInterestsByTypeId(interestTypeId);
    }

}
