package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.InterestBO;
import com.wisewin.backend.entity.bo.InterestSubclassBO;

import java.util.List;

/**
 * 二级兴趣
 * */
public interface InterestSubclassDAO {

    /**
     * 增加二级兴趣
     * param fatherId name adminId
     */
    Integer addInterestSubclass(InterestSubclassBO interestSubclassBO);

    /**
     * 删除二级兴趣
     */
    Integer delInterestSubclass(Integer id);

    /**
     * 修改二级兴趣
     * 只修改name
     * param name id adminId
     */
    Integer updInterestSubclass(InterestSubclassBO interestSubclassBO);

    /**
     * 通过一级兴趣的id 查询二级兴趣
     * @param interestId 一级兴趣的id
     */
    List<InterestSubclassBO> getInterestsSubclassByInterestId(Integer interestId);
}
