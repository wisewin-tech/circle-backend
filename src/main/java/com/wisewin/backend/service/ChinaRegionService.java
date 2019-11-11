package com.wisewin.backend.service;

import com.wisewin.backend.dao.ChinaRegionDAO;
import com.wisewin.backend.entity.bo.ChinaRegionBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ChinaRegionService {
    @Resource
    ChinaRegionDAO regionDAO;

    //查询所有地方
    public List<ChinaRegionBO> getChinaRegionBOList() {
        List<ChinaRegionBO> chinaRegionBOS = regionDAO.getChinaRegionBOList(0L);
        for (ChinaRegionBO region : chinaRegionBOS) {
            region.setChinaRegionBOList(regionDAO.getChinaRegionBOList(region.getId()));
        }
        return chinaRegionBOS;
    }

    //根据pid查询
    public List<ChinaRegionBO> getChinaRegionBOList(Long pid) {
        return regionDAO.getChinaRegionBOList(pid);
    }

    //根据id修改信息
    public Integer updChinaRegion(ChinaRegionBO chinaRegionBO) {
        return regionDAO.updChinaRegionBO(chinaRegionBO);
    }

    //根据id删除地区
    public Integer delChinaRegion(Long id) {
        regionDAO.delChinaRegionBOByPid(id);
        return regionDAO.delChinaRegionBOById(id);
    }

    //增加
    public Integer addChinaRegionBO(ChinaRegionBO chinaRegionBO){
        return regionDAO.addChinaRegionBO(chinaRegionBO);
    }

}
