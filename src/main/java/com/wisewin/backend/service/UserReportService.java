package com.wisewin.backend.service;

import com.wisewin.backend.dao.UserReportDAO;
import com.wisewin.backend.entity.bo.UserReportBO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class UserReportService {
    @Resource
    UserReportDAO userReportDAO;
    //根据状态 分页查询举报列表
    public List<UserReportBO> getUserReportList(String status,Integer pageOffset,Integer pageSize){
        return userReportDAO.getUserReportList(status,pageOffset,pageSize);
    }
    //根据状态 分页查询举报列表数量
    public Integer getUserReportListCount(String status){
        return userReportDAO.getUserReportListCount(status);
    }

    //修改状态
    public Integer updUserReport(UserReportBO userReportBO){
        return userReportDAO.updUserReport(userReportBO);
    }
}
