package com.wisewin.backend.service;

import com.wisewin.backend.dao.SundryDAO;
import com.wisewin.backend.entity.bo.SundryBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("sundryService")
@Transactional
public class SundryService {
    @Resource
    private SundryDAO sundryDAO;

    //查询"关于我们"的信息
    public SundryBO selectContent() {
        return sundryDAO.selectAbout();
    }

    //修改"关于我们"的信息
    /*
    没有数据时,添加;
    只有一条时,修改;
    大于一条时,数据异常
     */
    public boolean updateAbouUs(SundryBO aboutUsBO) {
        int i = sundryDAO.selectid();
        if (i==0){  //如果表里没有数据
            //新增内容
            sundryDAO.insertAboutUs(aboutUsBO);
        }else if(i==1) {  //如果表里有且只有一条数据

            //修改内容
            sundryDAO.updateAboutUs(aboutUsBO);
        }else{
            return false;
        }
        return true;

    }
}
