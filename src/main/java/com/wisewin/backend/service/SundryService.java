package com.wisewin.backend.service;

import com.wisewin.backend.dao.AboutUsDAO;
import com.wisewin.backend.entity.bo.AboutUsBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("aboutUsService")
@Transactional
public class AboutUsService {
    @Resource
    private AboutUsDAO aboutUsDAO;

    //查询"关于我们"的信息
    public AboutUsBO selectContent() {
        return aboutUsDAO.selectAbout();
    }

    //修改"关于我们"的信息
    /*
    没有数据时,添加;
    只有一条时,修改;
    大于一条时,数据异常
     */
    public boolean updateAbouUs(AboutUsBO aboutUsBO) {
        int i = aboutUsDAO.selectid();
        if (i==0){  //如果表里没有数据
            //新增内容
            aboutUsDAO.insertAboutUs(aboutUsBO);
        }else if(i==1) {  //如果表里有且只有一条数据

            //修改内容
            aboutUsDAO.updateAboutUs(aboutUsBO);
        }else{
            return false;
        }
        return true;

    }
}
