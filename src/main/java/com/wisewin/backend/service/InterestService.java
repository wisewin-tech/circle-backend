package com.wisewin.backend.service;

import com.wisewin.backend.dao.InterestDAO;
import com.wisewin.backend.dao.InterestTypeDAO;
import com.wisewin.backend.entity.bo.InterestBO;
import com.wisewin.backend.entity.bo.InterestTypeBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/*
* 兴趣
* */
@Service("InterestService")
@Transactional
public class InterestService {
    @Resource
    InterestDAO interestDAO;

    //增加兴趣分类
    public boolean addInterest(InterestBO interestBO){
        return interestDAO.addInterest(interestBO)>0;
    }

    //删除兴趣分类
    public boolean delInterest(Integer id,Integer type){
        return interestDAO.delInterest(id,type)>0;
    }

    //修改兴趣分类
    public boolean updInterest(InterestBO interestBO){
        return interestDAO.updInterest(interestBO)>0;
    }

    //查询兴趣分类
    public List<InterestBO> getInterestsByTypeId(Integer interestTypeId){
        return interestDAO.getInterestsByTypeId(interestTypeId);
    }

}
