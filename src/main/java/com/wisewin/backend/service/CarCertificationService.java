package com.wisewin.backend.service;

import com.wisewin.backend.dao.CarCertificationDAO;
import com.wisewin.backend.dao.UserDAO;
import com.wisewin.backend.dao.UserReportDAO;
import com.wisewin.backend.entity.bo.CarCertificationBO;
import com.wisewin.backend.entity.bo.UserBO;
import com.wisewin.backend.entity.bo.UserReportBO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CarCertificationService {
    @Resource
    CarCertificationDAO carCertificationDAO;
    @Resource
    UserDAO userDAO;

    //根据状态 分页查询举报列表
    public List<CarCertificationBO> getCarCertificationList(String status, Integer pageOffset, Integer pageSize, Date beforeTime,Date afterTime){
        return carCertificationDAO.getCarCertificationList(status,pageOffset,pageSize,beforeTime,afterTime);
    }
    //根据状态 分页查询举报列表数量
    public Integer getCarCertificationListCount(String status,Date beforeTime,Date afterTime){
        return carCertificationDAO.getCarCertificationListCount(status,beforeTime,afterTime);
    }
    //修改状态
    public Integer updCarCertification(CarCertificationBO carCertificationBO){
        UserBO userBO=new UserBO();
        userBO.setId(carCertificationBO.getUserId());
        userBO.setCarStatus(carCertificationBO.getCarCertificationStatus());
        userDAO.updateUser(userBO);
        return carCertificationDAO.updCarCertification(carCertificationBO);
    }
}