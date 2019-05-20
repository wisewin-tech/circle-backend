package com.wisewin.backend.service;

import com.wisewin.backend.dao.PatternDAO;
import com.wisewin.backend.entity.bo.PatternBO;

import javax.annotation.Resource;
import java.util.List;

/**
 * 王洋
 */
public class PatternService {

    @Resource
    PatternDAO patternDao;

    /**
     * 增加
     */
    public Integer addPatternBO(PatternBO patternBO){
        return patternDao.addPatternBO(patternBO);
    }


    /**
     *删除
     */
    public Integer delPatternBO(Integer id){
        return patternDao.delPatternBO(id);
    }

    /**
     *修改
     */
    public Integer updPatternBO(PatternBO patternBO){
        return patternDao.updPatternBO(patternBO);
    }

    /**
     *查询
     */
    public List<PatternBO> getPatternBO(Integer id){
        return patternDao.getPatternBO(id);
    }
}
