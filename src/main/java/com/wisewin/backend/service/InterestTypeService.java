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

/**
 * 二级兴趣
 * */
@Service("InterestTypeService")
@Transactional
public class InterestTypeService {

    @Resource
    InterestTypeDAO interestTypeDAO;

    @Resource
    InterestDAO interestDAO;

    @Resource
    InterestSubclassDAO interestSubclassDAO;

    /**
     * 增加兴趣分类
     * param fatherId name adminId
     */
    public boolean addInterestType(InterestTypeBO interestBO){
        return interestTypeDAO.addInterestType(interestBO)>0;
    }

    /**
     * 删除二级兴趣
     * 感觉没必要删除 子兴趣了
     */
    public boolean delInterestType(Integer id){
        //根据兴趣分类查找这个兴趣分类下的兴趣

        //删除兴趣分类下的兴趣下的子兴趣

        //删除兴趣分类下的兴趣
        //interestDAO.delInterest(null,id);

        //删除兴趣分类
        return interestTypeDAO.delInterestType(id)>0;
    }

    /**
     * 通过一级兴趣的id 查询二级兴趣
     */
    public List<InterestTypeBO> getInterestsType(){
        return interestTypeDAO.getInterestsType();
    }
}
