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
    public boolean updateAbouUs(String content) {
        int i = aboutUsDAO.selectid();
        if (i==0){  //如果表里没有数据
            AboutUsBO aboutUsBo=new AboutUsBO();
                    aboutUsBo.setContent(content);
            //添加
            aboutUsDAO.insertAboutUs(aboutUsBo);
        }else if(i==1) {  //如果表里有且只有一条数据
            AboutUsBO aboutUs=aboutUsDAO.selectAbout();
            aboutUs.setContent(content);
            //修改内容
            aboutUsDAO.updateAboutUs(aboutUs);
        }else{
            return false;
        }
        return true;

    }
}
