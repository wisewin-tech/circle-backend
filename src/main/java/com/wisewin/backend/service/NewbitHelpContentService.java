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
     * @param newbitHelpContentBO
     * @return
     */
    public Integer addNewbitHelpContent(NewbitHelpContentBO newbitHelpContentBO){
        return newbitHelpContentDAO.addNewbitHelpContent(newbitHelpContentBO);
    }

    /**
     * 删除新手帮助内容
     * @param id
     * @return
     */
    public boolean delNewbitHelpContentBO(Integer id){
        return newbitHelpContentDAO.delNewbitHelpContentBO(id);
    }

    /**
     * 修改新手帮助内容
     * @param newbitHelpContentBO
     * @return
     */
    public boolean updNewbitHelpContentBO(NewbitHelpContentBO newbitHelpContentBO){
        return newbitHelpContentDAO.updNewbitHelpContentBO(newbitHelpContentBO);
    }

    /**
     * 查询新手帮助有信息
     * @return
     */
    public List<NewbitHelpContentBO> getNewbitHelpContentBO(){
        return newbitHelpContentDAO.getNewbitHelpContentBO();
    }

}
