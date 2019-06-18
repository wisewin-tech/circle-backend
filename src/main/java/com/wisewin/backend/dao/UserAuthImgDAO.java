package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.UserAuthImgBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户认证信息
 */
public interface UserAuthImgDAO {

    /*
     *查询用户认证信息
     */
    List<UserAuthImgBO> getUserAuth(@Param("userId")Integer userId,@Param("status")String status);

    /*
     *修改用户认证信息状态
     */
    Integer updUserAuth(UserAuthImgBO authImgBO);


}
