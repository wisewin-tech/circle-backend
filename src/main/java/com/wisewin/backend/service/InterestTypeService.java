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
* 兴趣分类
* */
@Service("InterestTypeService")
@Transactional
public class InterestTypeService {
    @Resource
    InterestTypeDAO interestTypeDAO;

    @Resource
    InterestDAO interestDAO;

    //增加兴趣分类
    public boolean addInterestType(InterestTypeBO interestTypeBO){
        return interestTypeDAO.addInterestType(interestTypeBO)>0;
    }

    //删除兴趣分类 删除分类下的兴趣
    public boolean delInterestType(Integer id){

        return interestTypeDAO.delInterestType(id)>0;
    }

    //修改兴趣分类
    public boolean updInterestType(InterestTypeBO interestTypeBO){
        return interestTypeDAO.updInterestType(interestTypeBO)>0;
    }

    //查询兴趣分类
    public List<InterestTypeBO> getInterestTypes(){
        return interestTypeDAO.getInterestTypes();
    }

}
