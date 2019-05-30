package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.AboutUsBo;

public interface AboutUsDAO {

    //查询"关于我们"的信息
    AboutUsBo selectAbout();
    //查询表中数据是否为1
    Integer selectid();
    //修改信息
    void updateAboutUs(AboutUsBo aboutUs);
    //添加信息
    void insertAboutUs(AboutUsBo aboutUsBo);
}
