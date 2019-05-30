package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.NewbitHelpBO;
import com.wisewin.backend.entity.bo.NewbitHelpContentBO;

import java.util.List;


public interface NewbitHelpContentDAO {


    /**
     * 增加新手帮助内容
     * @param newbitHelpContentBO
     * @return
     */
    Integer addNewbitHelpContent(NewbitHelpContentBO newbitHelpContentBO);


    /**
     * 删除新手帮助内容
     * @param id
     * @return
     */
    boolean delNewbitHelpContentBO(Integer id);

    /**
     * 修改新手帮助内容
     * @param newbitHelpContentBO
     * @return
     */
    boolean updNewbitHelpContentBO(NewbitHelpContentBO newbitHelpContentBO);

    /**
     * 查询新手帮助有信息
     * @return
     */
    List<NewbitHelpContentBO> getNewbitHelpContentBO();

}
