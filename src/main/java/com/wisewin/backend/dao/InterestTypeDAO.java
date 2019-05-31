package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.InterestBO;
import com.wisewin.backend.entity.bo.InterestSubclassBO;
import com.wisewin.backend.entity.bo.InterestTypeBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 兴趣分类
 * */
public interface InterestTypeDAO {

    /**
     * 增加兴趣分类
     */
    Integer addInterestType(InterestTypeBO interestBO);

    /**
     * 删除兴趣
     */
    Integer delInterestType(Integer id);

    /**
     * 查询所有兴趣分类
     */
    List<InterestTypeBO> getInterestsType();
}
