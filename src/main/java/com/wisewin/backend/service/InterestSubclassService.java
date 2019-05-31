package com.wisewin.backend.service;

import com.wisewin.backend.dao.InterestSubclassDAO;
import com.wisewin.backend.entity.bo.InterestSubclassBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 二级兴趣
 * */
@Service("InterestSubclassService")
@Transactional
public class InterestSubclassService {

    @Resource
    InterestSubclassDAO interestSubclassDAO;

    /**
     * 增加二级兴趣
     * param fatherId name adminId
     */
    public boolean addInterestSubclass(InterestSubclassBO interestSubclassBO){
        return interestSubclassDAO.addInterestSubclass(interestSubclassBO)>0;
    }

    /**
     * 删除二级兴趣
     */
    public boolean delInterestSubclass(Integer id){
        return interestSubclassDAO.delInterestSubclass(id,null)>0;
    }

    /**
     * 通过一级兴趣的id 查询二级兴趣
     * @param interestId 一级兴趣的id
     */
    public List<InterestSubclassBO> getInterestsSubclassByInterestId(Integer interestId){
        return interestSubclassDAO.getInterestsSubclassByInterestId(interestId);
    }
}
