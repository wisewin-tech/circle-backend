package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.ChinaRegionBO;

import java.util.List;

public interface ChinaRegionDAO {
    //根据pid查询地方
    List<ChinaRegionBO> getChinaRegionBOList(Long pid);

    //根据id修改信息
    Integer updChinaRegionBO(ChinaRegionBO chinaRegionBO);
}
