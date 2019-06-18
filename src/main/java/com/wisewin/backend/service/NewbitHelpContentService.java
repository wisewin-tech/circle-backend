package com.wisewin.backend.service;

import com.wisewin.backend.dao.NewbitHelpContentDAO;
import com.wisewin.backend.entity.bo.NewbitHelpBO;
import com.wisewin.backend.entity.bo.NewbitHelpContentBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/*
* 新手帮助分类
* */
@Service("newbitHelpContentService")
@Transactional
public class NewbitHelpContentService {

    @Resource
    NewbitHelpContentDAO newbitHelpContentDAO;

    /**
     * 增加新手帮助内容
     * @param helpId
     * @param content
     * @return
     */
    public Integer addNewbitHelpContent(Integer helpId, String content){
        return newbitHelpContentDAO.addNewbitHelpContent(helpId, content);
    }

    /**
     * 修改新手帮助内容
     * @param helpId
     * @param content
     * @return
     */
    public boolean updNewbitHelpContent(Integer helpId, String content){
        return newbitHelpContentDAO.updNewbitHelpContent(helpId, content);
    }

    /**
     * 删除新手帮助内容
     * @param id
     * @return
     */
    public boolean delNewbitHelpContent(Integer id){
        return newbitHelpContentDAO.delNewbitHelpContent(id);
    }

    /**
     * 查询新手帮助内容
     * @return
     */
    public NewbitHelpContentBO getNewbitHelpContent(Integer helpId){
        return newbitHelpContentDAO.getNewbitHelpContent(helpId);
    }

}
