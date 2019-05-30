package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.UserBO;
import com.wisewin.backend.entity.param.UserParam;

import java.util.List;
import java.util.Map;

public interface UserDAO {
    //查询所有用户信息
    List<UserBO> selectAll(Map<String,Object> map);
    //(条件)查询总条数
    Integer selectCount(Map<String,Object> map);
    //通过id查询用户信息
    UserBO queryUserById(Integer id);

    //修改用户信息
    void updateUser(UserParam userParam);
    //通过id删除用户信息
    void delUserById(Integer id);

}
