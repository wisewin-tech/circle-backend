package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.NewbitHelpBO;
import com.wisewin.backend.entity.bo.PatternBO;

import java.util.List;

/**
 * 王洋
 */
public interface PatternDao {
    /**
     * 增加
     */
    Integer addPatternBO(PatternBO patternBO);


    /**
     *删除
     */
    Integer delPatternBO(Integer id);

    /**
     *修改
     */
    Integer updPatternBO(PatternBO patternBO);

    /**
     *查询
     */
    List<PatternBO> getPatternBO();
}
