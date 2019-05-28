package com.wisewin.backend.service;

import com.wisewin.backend.dao.AdminDAO;
import com.wisewin.backend.entity.bo.AdminBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by 王彬 on 2019/5/16.
 */
@Service("adminService")
@Transactional
public class AdminService {
    @Resource
    private AdminDAO adminDAO;

    public void updateAdmin(AdminBO admin) {
        adminDAO.updateAdmin(admin);
    }

    public AdminDAO queryAdmin(String id) {
        return adminDAO.queryAdmin(id);
    }


}
