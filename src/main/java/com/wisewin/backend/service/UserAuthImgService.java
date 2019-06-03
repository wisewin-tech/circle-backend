package com.wisewin.backend.service;

import com.wisewin.backend.dao.UserAuthImgDAO;
import com.wisewin.backend.entity.bo.UserAuthImgBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *用户认证信息
 */
@Service("UserAuthImgService")
@Transactional
public class UserAuthImgService {

    @Resource
    private UserAuthImgDAO userAuthImgDAO;

    /*
     *查询用户认证信息
     */
    public List<UserAuthImgBO> getUserAuth(Integer userId,String status){
        return userAuthImgDAO.getUserAuth(userId,status);
    }

    /*
     *修改用户认证信息状态
     */
    public Integer updUserAuth(UserAuthImgBO authImgBO){
        return userAuthImgDAO.updUserAuth(authImgBO);
    }



}
