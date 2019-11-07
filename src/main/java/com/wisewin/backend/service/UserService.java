package com.wisewin.backend.service;

import com.wisewin.backend.dao.UserDAO;
import com.wisewin.backend.entity.bo.TheGarageImgBO;
import com.wisewin.backend.entity.bo.UserBO;
import com.wisewin.backend.entity.dto.BackgroundCountDTO;
import com.wisewin.backend.entity.dto.GarageDTO;
import com.wisewin.backend.entity.dto.GarageImgDTO;
import com.wisewin.backend.entity.dto.UserBackgroundDTO;
import com.wisewin.backend.entity.param.UserParam;
import com.wisewin.backend.util.MD5Util;
import com.wisewin.backend.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
@Transactional
public class UserService {

    @Resource
    private UserDAO userDAO;

    /**
     * 查询用户信息列表
     */
    public List<UserBO> getUserList(Map<String, Object> map) {
        return userDAO.getUserList(map);
    }

    /**
     * 查询用户信息列表数量
     */
    public Integer getUserListCount(Map<String, Object> map) {
        return userDAO.getUserListCount(map);
    }

    /**
     * 修改用户信息
     * @param userParam
     * @return
     */
    public Integer updateUser(UserParam userParam){
        return userDAO.updateUser(userParam);
    }

    /**
     * 冻结用户
     * @param id
     */
    public void updateAccountStatus(Integer id){
        UserParam userParam = new UserParam();
        userParam.setId(id);
        userParam.setAccountStatus("frozen");
        userDAO.updateUser(userParam);
        return;
    }

    /**
     * 获取审核背景图
     * @param map
     * @return
     */
    public BackgroundCountDTO queryBackground(Map<String, Object> map){
        BackgroundCountDTO backgroundCountDTO = new BackgroundCountDTO();
        List<UserBackgroundDTO> list = userDAO.queryUserBackground(map);
        int i = userDAO.queryUserBackgroundCount(map);
        backgroundCountDTO.setList(list);
        backgroundCountDTO.setCount(i);
        return backgroundCountDTO;
    }

    /**
     * 获取车辆审核列表
     * @param map
     * @return
     */
    public GarageImgDTO queryGarage(Map<String, Object> map){
        GarageImgDTO garageImgDTO = new GarageImgDTO();
        List<GarageDTO> garageDTOS = userDAO.listGarage(map);
        int i = userDAO.garageListCount(map);
        garageImgDTO.setList(garageDTOS);
        garageImgDTO.setCount(i);
        return garageImgDTO;
    }

    /**
     * 获取车库 下面信息的图片
     * @param garageId
     * @return
     */
    public List<TheGarageImgBO> queryGarageImg(Integer garageId){
        return userDAO.queryGarageImg(garageId);
    }


}
