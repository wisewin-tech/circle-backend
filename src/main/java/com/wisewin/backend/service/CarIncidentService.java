package com.wisewin.backend.service;

import com.wisewin.backend.common.constants.CircleConstants;
import com.wisewin.backend.dao.CarIncidentDAO;
import com.wisewin.backend.dao.MatchingFriendEdDAO;
import com.wisewin.backend.entity.bo.CarIncidentBO;
import com.wisewin.backend.entity.bo.MatchingFriendEdBO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service
public class CarIncidentService {
    @Resource
    CarIncidentDAO carIncidentDAO;
    @Resource
    MatchingFriendEdDAO matchingFriendEdDAO;

    //根据条件查询事件
    public List<CarIncidentBO> getCarIncident(Map<String, Object> map){
        return carIncidentDAO.getCarIncident(map);
    }
    //根据条件查询事件
    public Integer getCarIncidentCount(Map<String, Object> map){
        return carIncidentDAO.getCarIncidentCount(map);
    }

}
