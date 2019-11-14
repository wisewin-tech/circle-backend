package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.CarIncidentBO;

import java.util.List;
import java.util.Map;

public interface CarIncidentDAO {
    //根据条件查询事件
    List<CarIncidentBO> getCarIncident(Map<String,Object> map);
    Integer getCarIncidentCount(Map<String,Object> map);

}
