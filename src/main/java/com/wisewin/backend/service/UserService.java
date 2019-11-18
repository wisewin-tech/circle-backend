package com.wisewin.backend.service;

import com.wisewin.backend.dao.InterestDAO;
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
    private InterestDAO interestDAO;
    @Resource
    private UserPictureDAO userPictureDAO;

    //查询用户信息列表
    public List<UserBO> getUserList(Map<String, Object> map) {
        List<UserBO> userBOS = userDAO.getUserList(map);
        return userBOS;
    }

    //根据用户查询模式的信息
    public List<ModelBO> getModelByUserId(Long userId) {
        List<ModelBO> modelBOList = userDAO.getModelByUserId(userId);
        for (ModelBO modelBO : modelBOList) {
            if (modelBO != null && modelBO.getName() != null) {
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
    public Integer updateUser(UserBO userParam) {
        return userDAO.updateUser(userParam);
    }

    //获取用户认证列表
    public List<UserCertificationBO> getUserCertification(String status,Integer pageOffset,Integer pageSize) {
        return userDAO.getUserCertification(status,pageOffset,pageSize);
    }

    //获取用户认证列表数量
    public Integer getUserCertificationCount(String status) {
        return userDAO.getUserCertificationCount(status);
    }

    //修改用户认证状态
    public void updUserCertificationStatus(UserCertificationBO userCertificationBO) {
        userDAO.updUserCertificationStatus(userCertificationBO);
        UserBO userParam = new UserBO();
        userParam.setCertificationStatus(userCertificationBO.getStatus());
        userParam.setId(userCertificationBO.getId());
        userDAO.updateUser(userParam);
    }

    //查询机器人
    public UserBO getRobotUser(Long id) {
        UserBO userBO=userDAO.getUserById(id);
        System.out.println(id);
        List<ModelBO> modelBOList = userDAO.getModelByUserId(id);
        System.out.println(modelBOList.size());
        System.out.println(modelBOList.size());
        System.out.println(modelBOList.size());
        for (ModelBO modelBO : modelBOList) {
            if (modelBO != null && modelBO.getName() != null) {
                //每个模式下的背景图
                modelBO.setPictureBOList(userPictureDAO.getPictureByModelId(modelBO.getId()));
                //每个模式下的兴趣分类，兴趣分类下包括兴趣
                modelBO.setInterestTypeBOList(interestTypeDAO.getInterestTypeList(modelBO.getId()));
            }
        }
        userBO.setModelBOList(modelBOList);
        return userBO;
    }

    //添加机器人
    public void addRobotUser(UserBO userBO) {
        //添加user
        userBO.setRobotStatus("yes");
        userBO.setUserStatus("no");
        userDAO.addUser(userBO);
        //循环添加模块信息
        for (ModelBO modelBO : userBO.getModelBOList()) {
            modelBO.setUserId(userBO.getId());
            userDAO.addModel(modelBO);
            //模块信息中循环添加背景图
            if(modelBO.getPictureBOList()!=null){
                for (UserPictureBO userPictureBO : modelBO.getPictureBOList()) {
                    userPictureBO.setModelId(modelBO.getId());
                }
            }
            if(modelBO.getPictureBOList().size()!=0){
                userPictureDAO.addUserPicture(modelBO.getPictureBOList());
            }
            //模块信息中循环添加兴趣
            ArrayList<UserInterestBO> userInterestBOS=new ArrayList<UserInterestBO>();
            for (InterestTypeBO interestTypeBO : modelBO.getInterestTypeBOList()) {
                if(interestTypeBO.getInterestStrList()!=null){
                    for (String interestName : interestTypeBO.getInterestStrList()) {
                        UserInterestBO userInterestBO=new UserInterestBO();
                        userInterestBO.setTypeId(interestTypeBO.getTypeId());
                        userInterestBO.setModelId(modelBO.getId());
                        userInterestBO.setInterestName(interestName);
                        userInterestBOS.add(userInterestBO);
                    }
                }
            }
            if(userInterestBOS.size()!=0){
                interestDAO.addInterestList(userInterestBOS);
            }
        }
    }

    //修改机器人信息
    public void updRobotUser(UserBO userParam) {
        userDAO.updateUser(userParam);
        for (ModelBO modelBO:userParam.getModelBOList()) {
            this.updRobotModel(modelBO);
        }
    }

    //修改机器人模式信息
    public void updRobotModel(ModelBO modelBO) {
        //修改模式信息
        userDAO.updateUserModel(modelBO);
        //重新添加模式下的背景图信息
        userPictureDAO.delUserPicture(modelBO.getId());
        userPictureDAO.addUserPicture(modelBO.getPictureBOList());
        //重新添加模式下的兴趣信息
        interestDAO.delUserInterest(modelBO.getId());
        ArrayList<UserInterestBO> userInterestBOS=new ArrayList<UserInterestBO>();
        for (InterestTypeBO interestTypeBO : modelBO.getInterestTypeBOList()) {
            if(interestTypeBO.getInterestStrList()!=null){
                for (String interestName : interestTypeBO.getInterestStrList()) {
                    UserInterestBO userInterestBO=new UserInterestBO();
                    userInterestBO.setTypeId(interestTypeBO.getTypeId());
                    userInterestBO.setModelId(modelBO.getId());
                    userInterestBO.setInterestName(interestName);
                    userInterestBOS.add(userInterestBO);
                }
            }
        }
        if(userInterestBOS.size()!=0){
            interestDAO.addInterestList(userInterestBOS);
        }
    }
}
