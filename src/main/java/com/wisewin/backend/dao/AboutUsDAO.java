package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.AboutUsBO;

public interface AboutUsDAO {

    //查询"关于我们"的信息
    AboutUsBO selectAbout();
    //查询表中数据是否为1
    Integer selectid();
    //修改信息
    void updateAboutUs(AboutUsBO aboutUs);
    //添加信息
    void insertAboutUs(AboutUsBO aboutUsBo);
}
