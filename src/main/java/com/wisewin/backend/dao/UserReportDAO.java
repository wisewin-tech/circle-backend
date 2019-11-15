package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.UserReportBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserReportDAO {
    //根据状态 分页查询举报列表
    List<UserReportBO> getUserReportList(@Param("status") String status, @Param("pageOffset")Integer pageOffset,@Param("pageSize") Integer pageSize);
    //根据状态 分页查询举报列表数量
    Integer getUserReportListCount(@Param("status")String status);

    //修改状态
    Integer updUserReport(UserReportBO userReportBO);
}
