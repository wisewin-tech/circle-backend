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

    //查询用户信息
    UserBO getUserById(Long id);
    //查询用户列表信息
    List<UserBO> getUserList(Map<String, Object> map);
    //查询用户列表条数
    Integer getUserListCount(Map<String, Object> map);
    //根据用户查询模式的信息
    List<ModelBO> getModelByUserId(Long userId);
    //修改用户信息
    Integer updateUser(UserBO userParam);
    //修改用户模式信息
    Integer updateUserModel(ModelBO modelBO);

    //查询用户认证列表
    List<UserCertificationBO> getUserCertification(@Param("status") String status,@Param("pageOffset") Integer pageOffset,@Param("pageSize") Integer pageSize);
    //查询用户认证列表
    Integer getUserCertificationCount(@Param("status") String status);
    //修改用户认证状态
    Integer updUserCertificationStatus(UserCertificationBO userCertificationBO);

    //查询总用户数
    Long getUserAllCount();
    //根据日期查询注册数
    Long getUserRegisteredCount(@Param("year")Integer year,@Param("month")Integer month,@Param("day")Integer day);
    //根据日期查询活跃数
    Long getUserActiveCount(@Param("year")Integer year,@Param("month")Integer month,@Param("day")Integer day);
    //分组查询每个模式下的匹配数
    List<StatisticalBO> getMatchingCount(@Param("year")Integer year,@Param("month")Integer month,@Param("day")Integer day);
}
