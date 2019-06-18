package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.CaseBO;
import java.util.List;

/**
 * 认证案例管理
 * */
public interface CaseDAO {

    Integer addCase(CaseBO caseImgBO);

    Integer updCase(CaseBO caseImgBO);

    List<CaseBO> getCaseImg();

}
