package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.TheGarageImgBO;
import com.wisewin.backend.entity.bo.UserBO;
import com.wisewin.backend.entity.dto.GarageDTO;
import com.wisewin.backend.entity.dto.UserBackgroundDTO;
import com.wisewin.backend.entity.param.UserParam;

import java.util.List;
import java.util.Map;

public interface UserDAO {
    //查询用户列表信息
    List<UserBO> getUserList(Map<String, Object> map);
    //查询用户列表条数
    Integer getUserListCount(Map<String, Object> map);
    //通过id查询用户信息
    UserBO queryUserById(Integer id);
    //修改用户信息
    Integer updateUser(UserParam userParam);


    /**
     * 获取背景图
     * @param map
     * @return
     */
    List<UserBackgroundDTO> queryUserBackground(Map<String, Object> map);

    /**
     *  获取符合条件的记录数
     * @param map
     * @return
     */
    int queryUserBackgroundCount(Map<String, Object> map);


    /**
     * 获取审核车辆信息列表
     * @param map
     * @return
     */
    List<GarageDTO> listGarage(Map<String, Object> map);

    /**
     * 获取审核车辆列表记录数
     * @param map
     * @return
     */
    int garageListCount(Map<String, Object> map);


    /**
     * 获取车辆审核下图片
     * @param id
     * @return
     */
    List<TheGarageImgBO> queryGarageImg(Integer id);
}
