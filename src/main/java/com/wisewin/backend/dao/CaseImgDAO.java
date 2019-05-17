package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.CaseImgBO;

/**
 * Created by 王彬 on 2019/5/16.
 */
public interface CaseImgDAO {

    /**
     * 根据id查询案例图片
     * @param id
     * @return
     */
    CaseImgBO queryCaseImg(String id);

    /**
     * 增加
     * @param caseImg
     */
    void insertCaseImg(CaseImgBO caseImg);

    /**
     * 修改
     * @param caseImg
     */
    void updateCaseImg(CaseImgBO caseImg);
}
