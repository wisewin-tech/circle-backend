package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.CarIncidentBO;

import java.util.List;
import java.util.Map;

public interface CarIncidentDAO {
    //根据条件查询事件
    List<CarIncidentBO> getCarIncident(Map<String,Object> map);
    Integer getCarIncidentCount(Map<String,Object> map);

    //根据用户id查询事件
    CarIncidentBO getCarIncidentById(Long userId);

    //添加事件
    Integer addCarIncident(CarIncidentBO carIncidentBO);
    //修改事件
    Integer updCarIncident(CarIncidentBO carIncidentBO);
}
