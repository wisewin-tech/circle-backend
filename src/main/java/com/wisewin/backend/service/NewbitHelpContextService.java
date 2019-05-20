package com.wisewin.backend.service;

import com.wisewin.backend.dao.NewbitHelpContextDao;
import com.wisewin.backend.entity.bo.NewbitHelpBO;
import com.wisewin.backend.entity.bo.NewbitHelpContextBO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
* 新手帮助分类
* */
@Service
public class NewbitHelpContextService {

    @Resource
    NewbitHelpContextDao newbitHelpContextDao;
    /**
     * 增加
     */
    public Integer addNewbitHelpContextBO(NewbitHelpContextBO newbitHelpContextBO){
        return newbitHelpContextDao.addNewbitHelpContextBO(newbitHelpContextBO);
    }


    /**
     *删除
     */
    public Integer delNewbitHelpContextBO(Integer id){
        return newbitHelpContextDao.delNewbitHelpContextBO(id);
    }

    /**
     *修改
     */
    public Integer updNewbitHelpContextBO(NewbitHelpContextBO newbitHelpContextBO){
        return newbitHelpContextDao.updNewbitHelpContextBO(newbitHelpContextBO);
    }

    /**
     *查询
     */
    public List<NewbitHelpBO> getNewbitHelpContextBO(){
        return newbitHelpContextDao.getNewbitHelpContextBO();
    }

}
