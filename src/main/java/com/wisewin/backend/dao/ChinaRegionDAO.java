package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.ChinaRegionBO;
import com.wisewin.backend.entity.bo.ChinaRegionBO2;

import java.util.List;

public interface ChinaRegionDAO {
    //根据pid查询地方
    List<ChinaRegionBO> getChinaRegionBOList(Long pid);
    //根据pid查询地方
    List<ChinaRegionBO2> getChinaRegionBOList2(Long pid);

    //根据id修改信息
    Integer updChinaRegionBO(ChinaRegionBO chinaRegionBO);

    //根据id删除地区
    Integer delChinaRegionBOById(Long id);

    //根据pid删除地区
    Integer delChinaRegionBOByPid(Long pid);

    //增加
    Integer addChinaRegionBO(ChinaRegionBO chinaRegionBO);
}
