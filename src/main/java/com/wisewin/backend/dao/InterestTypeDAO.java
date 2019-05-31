package com.wisewin.backend.dao;


import com.wisewin.backend.entity.bo.InterestTypeBO;

import java.util.List;

/**
 * 兴趣分类
 * */
public interface InterestTypeDAO {

    //增加兴趣分类
    Integer addInterestType(InterestTypeBO interestBO);

    //删除兴趣分类
    Integer delInterestType(Integer id);

    //修改兴趣分类
    Integer updInterestType(InterestTypeBO interestBO);

    //查询兴趣分类
    List<InterestTypeBO> getInterestTypes();
}
