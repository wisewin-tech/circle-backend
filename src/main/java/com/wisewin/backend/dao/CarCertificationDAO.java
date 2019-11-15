package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.CarCertificationBO;
import com.wisewin.backend.entity.bo.UserReportBO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CarCertificationDAO {
    //根据状态分页查询认证列表
    List<CarCertificationBO> getCarCertificationList(@Param("status") String status, @Param("pageOffset")Integer pageOffset, @Param("pageSize") Integer pageSize, @Param("beforeTime") Date beforeTime, @Param("afterTime") Date afterTime);
    //根据状态分页查询认证列表数量
    Integer getCarCertificationListCount(@Param("status")String status,@Param("beforeTime") Date beforeTime, @Param("afterTime") Date afterTime);

    //修改状态
    Integer updCarCertification(CarCertificationBO carCertificationBO);
}
