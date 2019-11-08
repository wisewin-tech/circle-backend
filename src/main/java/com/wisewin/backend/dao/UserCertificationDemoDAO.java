package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.UserCertificationDemoBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 认证案例管理
 */
public interface UserCertificationDemoDAO {

    /*
     * 添加用户认证demo
     */
    Integer addCertificationDemo(UserCertificationDemoBO userCertificationDemoBO);

    /*
     * 删除用户认证demo
     */
    Integer delCertificationDemo(Long demoId);

    /*
     * 查询用户认证demo
     */
    List<UserCertificationDemoBO> getCertificationDemoList();


}
