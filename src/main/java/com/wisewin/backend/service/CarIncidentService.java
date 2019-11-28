package com.wisewin.backend.service;

import com.wisewin.backend.dao.CarIncidentDAO;
import com.wisewin.backend.dao.MatchingFriendEdDAO;
import com.wisewin.backend.entity.bo.CarIncidentBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class CarIncidentService {
    @Resource
    CarIncidentDAO carIncidentDAO;

    //根据条件查询事件
    public List<CarIncidentBO> getCarIncident(Map<String, Object> map){
        return carIncidentDAO.getCarIncident(map);
    }
    //根据条件查询事件
    public Integer getCarIncidentCount(Map<String, Object> map){
        return carIncidentDAO.getCarIncidentCount(map);
    }

}
