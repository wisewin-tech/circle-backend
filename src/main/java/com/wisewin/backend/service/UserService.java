package com.wisewin.backend.service;

import com.alibaba.druid.support.logging.Log;
import com.wisewin.backend.dao.*;
import com.wisewin.backend.entity.bo.*;
import com.wisewin.backend.entity.dto.*;
import com.wisewin.backend.entity.param.UserParam;
import com.wisewin.backend.util.MD5Util;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.web.controller.Test;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

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
    @Resource
    private CarIncidentDAO carIncidentDAO;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    //查询用户信息列表
    public List<UserBO> getUserList(Map<String, Object> map) {
        List<UserBO> userBOS = userDAO.getUserList(map);
        return userBOS;
    }

    //根据用户查询模式的信息
    public List<ModelBO> getModelByUserId(Long userId) {
        log.info("start getModelByUserId..................................");
        log.info("userId:{}",userId);
        List<ModelBO> modelBOList = userDAO.getModelByUserId(userId);
        for (ModelBO modelBO : modelBOList) {
            if (modelBO != null && modelBO.getName() != null) {
                //每个模式下的背景图
                modelBO.setPictureBOList(userPictureDAO.getPictureByModelId(modelBO.getId()));
                //每个模式下的兴趣分类，兴趣分类下包括兴趣
                modelBO.setInterestTypeBOList(interestTypeDAO.getInterestTypeList(modelBO.getId()));
            }
        }
        log.info("result:{}", modelBOList);
        log.info("end getModelByUserId.............................................");

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
        log.info("start updUserCertificationStatus..................................");
        log.info("UserCertificationBO:{}",userCertificationBO);
        userDAO.updUserCertificationStatus(userCertificationBO);
        UserBO userParam = new UserBO();
        userParam.setCertificationStatus(userCertificationBO.getStatus());
        userParam.setId(userCertificationBO.getId());
        userDAO.updateUser(userParam);
        log.info("end updUserCertificationStatus.............................................");
    }

    //查询机器人
    public UserBO getRobotUser(Long id) {
        log.info("start getRobotUser..................................");
        log.info("id:{}",id);
        UserBO userBO=userDAO.getUserById(id);
        if(userBO==null){
            return null;
        }
        System.out.println(id);
        List<ModelBO> modelBOList = userDAO.getModelByUserId(id);
        for (ModelBO modelBO : modelBOList) {
            if (modelBO != null && modelBO.getName() != null) {
                //每个模式下的背景图
                modelBO.setPictureBOList(userPictureDAO.getPictureByModelId(modelBO.getId()));
                //每个模式下的兴趣分类，兴趣分类下包括兴趣
                modelBO.setInterestTypeBOList(interestTypeDAO.getInterestTypeList(modelBO.getId()));
            }
        }
        userBO.setModelBOList(modelBOList);
        //查询事件
        CarIncidentBO carIncidentBO=new CarIncidentBO();
        CarIncidentBO carIncident=carIncidentDAO.getCarIncidentById(id);
        if(carIncident!=null){
            carIncidentBO=carIncident;
        }
        userBO.setCarIncidentBO(carIncidentBO);

        //时间类型为 null
        userBO.setCreateTime(null);
        userBO.setUserStatus(null);
        userBO.getCarIncidentBO().setCreateTime(null);
        userBO.getCarIncidentBO().setUpdateTime(null);
        List<ModelBO> modelBOS=userBO.getModelBOList();
        for (ModelBO modelBO:modelBOS) {
            modelBO.setUpdateTime(null);
            List<UserPictureBO> userPictureBOS=modelBO.getPictureBOList();
            for (UserPictureBO userPictureBO:userPictureBOS) {
                userPictureBO.setCreateTime(null);
                userPictureBO.setUpdateTime(null);
            }
        }
        log.info("result:{}", userBO);
        log.info("end getRobotUser.............................................");
        return userBO;
    }

    //添加机器人
    public void addRobotUser(UserBO userBO) {
        log.info("start addRobotUser..................................");
        log.info("userBO:{}",userBO);
        //添加user
        userBO.setRobotStatus("yes");
        userBO.setUserStatus("no");
        userDAO.addUser(userBO);
        userDAO.updUserPOINT(userBO.getId());
        //添加事件
        CarIncidentBO carIncidentBO=userBO.getCarIncidentBO();
        if(carIncidentBO!=null){
            carIncidentBO.setIncidentStatus("yes");
            carIncidentDAO.addCarIncident(carIncidentBO);
        }

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
        log.info("end addRobotUser.............................................");
    }

    //修改机器人信息
    public void updRobotUser(UserBO userParam) {
        userDAO.updateUser(userParam);
        for (ModelBO modelBO:userParam.getModelBOList()) {
            this.updRobotModel(modelBO);
        }
        //修改事件
        CarIncidentBO carIncidentBO=userParam.getCarIncidentBO();
        if(carIncidentBO!=null&&carIncidentBO.getId()!=null&&carIncidentBO.getId()!=0){
            carIncidentBO.setIncidentStatus("yes");
            carIncidentDAO.updCarIncident(carIncidentBO);
        }
        userDAO.updUserPOINT(userParam.getId());
    }

    //修改机器人模式信息
    public void updRobotModel(ModelBO modelBO) {
        log.info("start updRobotModel..................................");
        log.info("modelBO:{}",	modelBO);
        //修改模式信息
        modelBO.setId(modelBO.getModelId());
        userDAO.updateUserModel(modelBO);
        //重新添加模式下的背景图信息
        userPictureDAO.delUserPicture(modelBO.getId());
        //模块信息中循环添加背景图
        if(modelBO.getPictureBOList()!=null){
            for (UserPictureBO userPictureBO : modelBO.getPictureBOList()) {
                userPictureBO.setModelId(modelBO.getId());
            }
            userPictureDAO.addUserPicture(modelBO.getPictureBOList());
        }
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
        log.info("end updRobotModel.............................................");
    }



    public void test(){
        String[] models=new String[]{"date","friend","car"};
        for(int i=0;i<1000;i++){
            System.out.println(i+"...............");
            UserDTO userDTO=new UserDTO();
            userDTO.setPhone(Test.getTel());
            userDTO.setPassword(userDTO.getPhone());
            String[] strings = Test.randomLonLat(73.66, 135.05, 3.86, 53.55);
            userDTO.setLongitude(new Double(strings[0]));
            userDTO.setLatitude(new Double(strings[1]));
            userDTO.setCertificationStatus(Test.getYesNo());
            userDTO.setCarStatus(Test.getYesNo());
            userDTO.setUserStatus("no");
            userDTO.setRobotStatus("no");
            userDTO.setRobotStatus("no");
            userDTO.setDriver(Test.getYesNo());
            userPictureDAO.addTestUser(userDTO); //用户信息
            //添加模式信息
            for(String mo:models){
                ModelDTO modelDTO=new ModelDTO();
                modelDTO.setModel(mo);
                modelDTO.setUserId(userDTO.getId());
                String name=Test.getName();
                modelDTO.setName(name);
                modelDTO.setDescribe("大家好我是"+name+",是兄弟就来砍我!");
                String sex=Test.getSex();
                modelDTO.setSex(sex);
                modelDTO.setSexCount(0L);
                Calendar dateBetweenMaxAndMin = Test.getDateBetweenMaxAndMin();
                modelDTO.setBirthday(dateBetweenMaxAndMin.getTime());
                modelDTO.setConstellation(Test.getConstellation(dateBetweenMaxAndMin.get(Calendar.MONTH)+1,dateBetweenMaxAndMin.get(Calendar.DAY_OF_MONTH)));
                modelDTO.setHeight(String.valueOf(Test.getNum(150,200)));
                modelDTO.setWeight(String.valueOf(Test.getNum(40,90)));
                modelDTO.setEducation(Test.educationStr());
                modelDTO.setBirthplace(Test.getRoad());
                modelDTO.setSearchDistance(String.valueOf(70));
                if(sex.equals("男")){
                    modelDTO.setSearchSex("女");
                }else{
                    modelDTO.setSearchSex("男");

                }
                modelDTO.setSearchAge(18+"-"+60);
                modelDTO.setSexStatus(Test.getYesNo());
                modelDTO.setCarCertificationStatus(Test.getYesNo());
                modelDTO.setBeLikeCount(0L);
                modelDTO.setBeSuperLikeCount(0L);
                modelDTO.setSuperLikeCount(0L);
                modelDTO.setSuperLikeCountTime(new Date());
                modelDTO.setBeShieldingCount(0L);
                modelDTO.setFirst("no");
                modelDTO.setSlideCount(0L);
                modelDTO.setSlideTime(new Date());
                userPictureDAO.addTestModel(modelDTO);

                for(int x=0;x<Test.getNum(3,5);x++){
                    InterestDTO interestDTO=new InterestDTO();
                    interestDTO.setTypeId(1L);
                    interestDTO.setModelId(modelDTO.getId());
                    String   first=Test.jianshen[Test.getNum(0,Test.jianshen.length-1)];
                    interestDTO.setInterestName(first);
                    userPictureDAO.addTestInterest(interestDTO);
                }

                for(int x=0;x<Test.getNum(3,5);x++){
                    InterestDTO interestDTO=new InterestDTO();
                    interestDTO.setTypeId(2L);
                    interestDTO.setModelId(modelDTO.getId());
                    String   first=Test.chongwu[Test.getNum(0,Test.chongwu.length-1)];
                    interestDTO.setInterestName(first);
                    userPictureDAO.addTestInterest(interestDTO);
                }

                for(int x=0;x<Test.getNum(3,5);x++){
                    InterestDTO interestDTO=new InterestDTO();
                    interestDTO.setTypeId(3L);
                    interestDTO.setModelId(modelDTO.getId());
                    String   first=Test.lvyou[Test.getNum(0,Test.lvyou.length-1)];
                    interestDTO.setInterestName(first);
                    userPictureDAO.addTestInterest(interestDTO);
                }


                for(int x=0;x<Test.getNum(3,5);x++){
                    InterestDTO interestDTO=new InterestDTO();
                    interestDTO.setTypeId(4L);
                    interestDTO.setModelId(modelDTO.getId());
                    String   first=Test.meijiu[Test.getNum(0,Test.meijiu.length-1)];
                    interestDTO.setInterestName(first);
                    userPictureDAO.addTestInterest(interestDTO);
                }

                for(int x=0;x<Test.getNum(3,5);x++){
                    InterestDTO interestDTO=new InterestDTO();
                    interestDTO.setTypeId(5L);
                    interestDTO.setModelId(modelDTO.getId());
                    String   first=Test.bianqian[Test.getNum(0,Test.bianqian.length-1)];
                    interestDTO.setInterestName(first);
                    userPictureDAO.addTestInterest(interestDTO);
                }


                for(int x=0;x<Test.getNum(6,7);x++){
                    PictureDTO pictureDTO=new PictureDTO();
                    pictureDTO.setModelId(modelDTO.getId());
                    pictureDTO.setPictureStatus("audit");
                    if(sex.equals("男")){
                        pictureDTO.setPictureUrl(Test.nan[Test.getNum(0,Test.nan.length-1)]);
                    }else{
                        pictureDTO.setPictureUrl(Test.nv[Test.getNum(0,Test.nv.length-1)]);
                    }
                    pictureDTO.setSort(new Long(x));
                    userPictureDAO.addTestImg(pictureDTO);
                }

            }


            int num = Test.getNum(0, 10);
            if(num%3==0){
                IncidentDTO incidentDTO=new IncidentDTO();
                incidentDTO.setUserId(userDTO.getId());
                String[] split = Test.getRoad().split("-");
                incidentDTO.setOrigin(split[0]);
                incidentDTO.setDestination(split[1]);
                incidentDTO.setIncidentStatus("yes");
                incidentDTO.setIncidentTime(Test.getNum(0, 23)+":"+Test.getNum(1, 59)+":"+Test.getNum(1, 59));
                incidentDTO.setIncident("兜风");
                incidentDTO.setCreateTime(new Date());
                userPictureDAO.addTestIncident(incidentDTO);
            }
        }

        userPictureDAO.testOption();
    }
}
