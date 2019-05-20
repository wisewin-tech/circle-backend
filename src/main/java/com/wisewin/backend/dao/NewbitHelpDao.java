package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.NewbitHelpBO;

import java.util.List;

/**
 * 王洋
 */
public interface NewbitHelpDao {

    /**
     * 增加
     */
    Integer addNewbitHelp(NewbitHelpBO newbitHelpBO);


    /**
     *删除
     */
    Integer delNewbitHelp(Integer id);

    /**
     *修改
     */
    Integer updNewbitHelp(NewbitHelpBO newbitHelpBO);

    /**
     *查询
     */
    List<NewbitHelpBO> getNewbitHelp();
}
