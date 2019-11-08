package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.*;
import com.wisewin.backend.entity.param.UserParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDAO {
    //添加用户信息
    Integer addUser(UserBO userBO);
    //添加模块信息
    Integer addModel(ModelBO modelBO);
    //添加兴趣信息
    Integer addInterest(UserInterestBO interestBO);

    //查询用户列表信息
    List<UserBO> getUserList(Map<String, Object> map);
    //查询用户列表条数
    Integer getUserListCount(Map<String, Object> map);
    //根据用户查询模式的信息
    List<ModelBO> getModelByUserId(Long userId);
    //修改用户信息
    Integer updateUser(UserParam userParam);

    //查询用户认证列表
    List<UserCertificationBO> getUserCertification(@Param("status") String status,@Param("pageOffset") Integer pageOffset,@Param("pageSize") Integer pageSize);
    //查询用户认证列表
    Integer getUserCertificationCount(@Param("status") String status);
    //修改用户认证状态
    Integer updUserCertificationStatus(UserCertificationBO userCertificationBO);

}
