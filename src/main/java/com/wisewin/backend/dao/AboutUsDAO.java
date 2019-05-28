package com.wisewin.backend.dao;


import com.wisewin.backend.entity.bo.AboutUsBO;

public interface AboutUsDAO {

    /**
     * 查询"关于我们"的信息
     * @return
     */
    AboutUsBO selectAbout();

    /**
     * 查询表中数据是否为1
     * @return
     */
    Integer selectid();

    /**
     * 修改信息
     * @param aboutUs
     */
    void updateAboutUs(AboutUsBO aboutUs);

    /**
     * 添加信息
     * @param aboutUsBO
     */
    void insertAboutUs(AboutUsBO aboutUsBO);
    //void insertAboutUs(AboutUsBO aboutUsBO,Integer createUserId);
}
