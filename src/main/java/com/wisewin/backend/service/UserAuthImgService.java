package com.wisewin.backend.service;

import com.wisewin.backend.dao.UserAuthImgDAO;
import com.wisewin.backend.entity.bo.UserAuthImgBO;

import javax.annotation.Resource;

/**
 * Created by 王彬 on 2019/5/20.
 */
public class UserAuthImgService {

    @Resource
    private UserAuthImgDAO userAuthImgDAO;

    public UserAuthImgBO queryUserAuthById(String id){
        return userAuthImgDAO.queryUserAuthById(id);
    }

    public void updateUserAuth(UserAuthImgBO userAuthImg){
        userAuthImgDAO.updateUserAuth(userAuthImg);
    }

    public void insetUserAuth(UserAuthImgBO userAuthImg){
        userAuthImgDAO.insetUserAuth(userAuthImg);
    }


}
