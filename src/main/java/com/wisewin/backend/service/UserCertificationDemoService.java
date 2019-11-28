package com.wisewin.backend.service;

import com.wisewin.backend.dao.UserCertificationDemoDAO;
import com.wisewin.backend.entity.bo.UserCertificationDemoBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

/**
 *认证案例管理
 */
@Service
@Transactional
public class UserCertificationDemoService {
    @Resource
    UserCertificationDemoDAO userCertificationDemoDAO;

    /*
     * 添加用户认证demo
     */
    public Integer addCertificationDemo(UserCertificationDemoBO userCertificationDemoBO){
        return userCertificationDemoDAO.addCertificationDemo(userCertificationDemoBO);
    }

    /*
     * 删除用户认证demo
     */
    public Integer delCertificationDemo(Long demoId){
        return userCertificationDemoDAO.delCertificationDemo(demoId);
    }

    /*
     * 查询用户认证demo
     */
    public List<UserCertificationDemoBO> getCertificationDemoList(){
        return userCertificationDemoDAO.getCertificationDemoList();
    }


}
