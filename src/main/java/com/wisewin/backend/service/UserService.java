package com.wisewin.backend.service;

import com.wisewin.backend.dao.InterestTypeDAO;
import com.wisewin.backend.dao.UserDAO;
import com.wisewin.backend.dao.UserPictureDAO;
import com.wisewin.backend.entity.bo.*;
import com.wisewin.backend.entity.dto.BackgroundCountDTO;
import com.wisewin.backend.entity.dto.GarageDTO;
import com.wisewin.backend.entity.dto.GarageImgDTO;
import com.wisewin.backend.entity.dto.UserBackgroundDTO;
import com.wisewin.backend.entity.param.UserParam;
import com.wisewin.backend.util.MD5Util;
import com.wisewin.backend.util.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
@Transactional
public class UserService {
    @Resource
    private UserDAO userDAO;
    @Resource
    private InterestTypeDAO interestTypeDAO;
    @Resource
    private UserPictureDAO userPictureDAO;

    //查询用户信息列表
    public List<UserBO> getUserList(Map<String, Object> map) {
        List<UserBO> userBOS=userDAO.getUserList(map);
        return userBOS;
    }
    //根据用户查询模式的信息
    public List<ModelBO> getModelByUserId(Long userId){
        List<ModelBO> modelBOList=userDAO.getModelByUserId(userId);
        for (ModelBO modelBO:modelBOList) {
            if(modelBO!=null&&modelBO.getName()!=null){
                //每个模式下的背景图
                modelBO.setPictureBOList(userPictureDAO.getPictureByModelId(modelBO.getId()));
                //每个模式下的兴趣分类，兴趣分类下包括兴趣
                modelBO.setInterestTypeBOList(interestTypeDAO.getInterestTypeList(modelBO.getId()));
            }
        }
        return modelBOList;
    }
    //查询用户信息列表数量
    public Integer getUserListCount(Map<String, Object> map) {
        return userDAO.getUserListCount(map);
    }
    //修改用户信息
    public Integer updateUser(UserParam userParam){
        return userDAO.updateUser(userParam);
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
