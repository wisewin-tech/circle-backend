package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.NewbitHelpBO;
import com.wisewin.backend.entity.bo.NewbitHelpContextBO;

import java.util.List;

/*
* 新手帮助分类
* */
public interface NewbitHelpContextDao {


    /**
     * 增加
     */
    Integer addNewbitHelpContextBO(NewbitHelpContextBO newbitHelpContextBO);


    /**
     *删除
     */
    Integer delNewbitHelpContextBO(Integer id);

    /**
     *修改
     */
    Integer updNewbitHelpContextBO(NewbitHelpContextBO newbitHelpContextBO);

    /**
     *查询
     */
    List<NewbitHelpBO> getNewbitHelpContextBO();

}
