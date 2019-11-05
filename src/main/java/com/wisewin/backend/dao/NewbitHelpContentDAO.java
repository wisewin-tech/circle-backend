package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.NewbitHelpBO;
import com.wisewin.backend.entity.bo.NewbitHelpContentBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface NewbitHelpContentDAO {


    /**
     * 增加新手帮助内容
     * @param newbitHelpContentBO
     * @return
     */
    Integer addNewbitHelpContent(NewbitHelpContentBO newbitHelpContentBO);

    /**
     * 修改新手帮助内容
     * @param helpId
     * @param content
     * @return
     */
    boolean updNewbitHelpContent(@Param("helpId") Integer helpId,@Param("content") String content);

    /**
     * 删除新手帮助内容
     * @param id
     * @return
     */
    boolean delNewbitHelpContent(Integer id);

    /**
     * 查询新手帮助内容
     * @return
     */
    NewbitHelpContentBO getNewbitHelpContent(Integer helpId);

}
