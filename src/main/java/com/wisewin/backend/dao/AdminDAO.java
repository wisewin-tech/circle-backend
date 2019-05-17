package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.TestBO;

public interface AdminDAO {
    /**
     * 通过手机号查找管理员信息
     * @param mobile
     * @return UserDO
     */
    TestBO queryAdminInfoByMobile(String mobile);

    /**
     * 注册管理员信息
     * @param admin
     * @return
     */
    int adminRegister(TestBO admin);

    /**
     * 查找用户手机号是否注册过
     * @param mobile
     * @return
     */
    int selectCountByMobile(String mobile);

    /**
     * 修改admin信息
     * @param admin
     * @return
     */
    void updateAdmin(AdminBO admin);

    /**
     * 根据id查询admin
     * @param id
     * @return
     */
    AdminDAO queryAdmin(String id);
}
