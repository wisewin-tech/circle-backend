package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.InterestTypeBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InterestTypeDAO {
    //获取兴趣分类 和兴趣
    List<InterestTypeBO> getInterestTypeList(@Param("modelId")Long modelId);
}
