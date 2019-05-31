package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.InterestBO;
import com.wisewin.backend.entity.bo.InterestTypeBO;

import java.util.List;

/**
 * 兴趣分类下的一级兴趣
 * */
public interface InterestDAO {

    //增加兴趣
    Integer addInterest(InterestBO interestBO);

    /**
     * 删除兴趣
     * @param id 兴趣的id
     * @param typeId 一个兴趣分类的id
     * 传入一个id 进行删除
     */
    Integer delInterest(Integer id,Integer typeId);

    //修改兴趣
    Integer updInterest(InterestBO interestBO);

    //查询兴趣
    List<InterestBO> getInterestsByTypeId(Integer interestTypeId);
}
