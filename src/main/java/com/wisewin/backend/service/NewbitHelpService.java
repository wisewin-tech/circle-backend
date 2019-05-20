package com.wisewin.backend.service;

import com.wisewin.backend.dao.NewbitHelpDAO;
import com.wisewin.backend.entity.bo.NewbitHelpBO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 王洋
 */
@Service
public class NewbitHelpService {

    @Resource
    NewbitHelpDAO newbitHelpDao;
    /**
     * 增加
     */
     public Integer addNewbitHelp(NewbitHelpBO newbitHelpBO){
         return newbitHelpDao.addNewbitHelp(newbitHelpBO);
     }


    /**
     *删除
     */
    public Integer delNewbitHelp(Integer id){
        return newbitHelpDao.delNewbitHelp(id);
    }

    /**
     *修改
     */
    public Integer updNewbitHelp(NewbitHelpBO newbitHelpBO){
        return newbitHelpDao.updNewbitHelp(newbitHelpBO);
    }

    /**
     *查询
     */
    public List<NewbitHelpBO> getNewbitHelp (Integer id){
        return newbitHelpDao.getNewbitHelp(id);
    }
}
