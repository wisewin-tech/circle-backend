package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.InterestBO;
import com.wisewin.backend.entity.bo.InterestTypeBO;
import org.apache.ibatis.annotations.Param;

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
    Integer delInterest(@Param("id") Integer id,@Param("typeId") Integer typeId);

    //通过兴趣分类id查询分类下的兴趣
    List<InterestBO> getInterestsByTypeId(Integer interestTypeId);
}
