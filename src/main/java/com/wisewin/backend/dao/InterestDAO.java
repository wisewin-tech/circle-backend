package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.InterestBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 兴趣分类下的一级兴趣
 * */
public interface InterestDAO {

    /**
     * 查询兴趣
     * @param typeId 兴趣分类id
     * @return
     */
    List<InterestBO> queryInterestByTypeId(@Param("typeId") Integer typeId);

    /**
     * 添加兴趣
     * @param interestBO
     * @return
     */
    Integer addInterest(InterestBO interestBO);

    /**
     * 修改兴趣
     *
     *
     */
    Integer updateInterest(InterestBO interestBO);
    /**
     * 删除兴趣
     * @param id  兴趣id
     * @return
     */
    Integer delInterest(@Param("id") Integer id);

    /**
     * 查询兴趣分类
     */
    List<InterestBO> queryInterestType();
}
