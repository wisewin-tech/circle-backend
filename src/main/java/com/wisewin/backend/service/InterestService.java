package com.wisewin.backend.service;

import com.wisewin.backend.dao.InterestDAO;
import com.wisewin.backend.entity.bo.InterestBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service("InterestService")
@Transactional
public class InterestService {
    static final Logger log = LoggerFactory.getLogger(InterestService.class);

    @Resource
    InterestDAO interestDAO;

    /**
     * 查询兴趣
     * @param typeId 兴趣分类id
     * @return
     */
    public List<InterestBO> queryInterestByTypeId(Integer typeId){
        return interestDAO.queryInterestByTypeId(typeId);
    }

    /**
     * 添加兴趣
     * @param interestBO
     * @return
     */
    public Integer addInterest(InterestBO interestBO){
        return interestDAO.addInterest(interestBO);
    }

    /**
     * 删除兴趣
     * @param id  兴趣id
     * @return
     */
    public Integer delInterest(Integer id){
        return interestDAO.delInterest(id);
    }

    /**
     * 查询兴趣分类
     */
    public List<InterestBO> queryInterestType(){
        return interestDAO.queryInterestType();
    }

}
