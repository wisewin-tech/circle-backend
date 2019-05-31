package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.InterestBO;
import com.wisewin.backend.entity.bo.InterestSubclassBO;
import org.apache.ibatis.annotations.Param;

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
    Integer delInterestSubclass(@Param("id") Integer id,@Param("fatherId")Integer fatherId);

    /**
     * 通过一级兴趣的id 查询二级兴趣
     * @param interestId 一级兴趣的id
     */
    List<InterestSubclassBO> getInterestsSubclassByInterestId(Integer interestId);
}
