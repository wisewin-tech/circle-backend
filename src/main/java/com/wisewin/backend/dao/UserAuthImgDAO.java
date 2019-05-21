package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.UserAuthImgBO;

/**
 * Created by 王彬 on 2019/5/20.
 */
public interface UserAuthImgDAO {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    UserAuthImgBO queryUserAuthById(String id);

    /**
     * 修改
     * @param userAuthImg
     */
    void updateUserAuth(UserAuthImgBO userAuthImg);

    /**
     * 添加
     * @param userAuthImg
     */
    void insetUserAuth(UserAuthImgBO userAuthImg);
}
