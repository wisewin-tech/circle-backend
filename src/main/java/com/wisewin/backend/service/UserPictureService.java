package com.wisewin.backend.service;

import com.wisewin.backend.dao.UserPictureDAO;
import com.wisewin.backend.entity.bo.ModelBO;
import com.wisewin.backend.entity.bo.UserPictureBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("UserPictureService")
@Transactional
public class UserPictureService {
    @Resource
    private UserPictureDAO userPictureDAO;

    //根据审核状态查询背景墙列表
    public List<UserPictureBO> getPictureByStatus(String status,Long sort, String model, Integer pageOffset, Integer pageSize){
        return userPictureDAO.getPictureByStatus(status,sort,model,pageOffset,pageSize);
    }
    //根据审核状态查询背景墙列表数量
    public Integer getPictureByStatusCount(String status,Long sort, String model){
        return userPictureDAO.getPictureByStatusCount(status,sort,model);
    }
    //修改背景墙状态
    public Integer updPictureById(UserPictureBO userPictureBO){
        return userPictureDAO.updPictureById(userPictureBO);
    }

}
