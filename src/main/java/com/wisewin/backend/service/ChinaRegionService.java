package com.wisewin.backend.service;

import com.wisewin.backend.dao.ChinaRegionDAO;
import com.wisewin.backend.entity.bo.ChinaRegionBO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ChinaRegionService {
    @Resource
    ChinaRegionDAO regionDAO;
    //查询所有地方
    public List<ChinaRegionBO> getChinaRegionBOList(){
        List<ChinaRegionBO> chinaRegionBOS=regionDAO.getChinaRegionBOList(0L);
        for (ChinaRegionBO region:chinaRegionBOS) {
            region.setChinaRegionBOList(regionDAO.getChinaRegionBOList(region.getId()));
        }
        return chinaRegionBOS;
    }

    //根据id修改信息
    public Integer updChinaRegion(ChinaRegionBO chinaRegionBO){
        return regionDAO.updChinaRegionBO(chinaRegionBO);
    }

}
