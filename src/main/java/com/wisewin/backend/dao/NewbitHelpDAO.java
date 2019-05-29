package com.wisewin.backend.dao;

import com.wisewin.backend.entity.param.NewditHelpParam;
import com.wisewin.backend.entity.bo.NewbitHelpBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface NewbitHelpDAO {

    /**
     * 查询新手帮助信息
     * @return
     */
    List<NewbitHelpBO> selectNewbitHelp();

    /**
     * 新增新手帮助信息
     * @param newditHelpParam
     * @return
     */
    Integer insertNewbitHelp(NewditHelpParam newditHelpParam);

    /**
     * 删除新手帮助信息
     * @param id
     * @return
     */
    boolean deleteNewbitHelp(Integer id);

    /**
     * 修改新手帮助信息
     * @param param
     * @return
     */
    boolean editNewditHelp(NewditHelpParam param);
}
