package com.wisewin.backend.service;


import com.wisewin.backend.dao.AdminDAO;
import com.wisewin.backend.entity.bo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("adminService")
@Transactional
public class AdminService {
    @Resource
    private AdminDAO adminDAO;

    /**
     * 根据手机号查找管理员信息
     * 管理员登录
     * @param mobile
     * @return
     */
    public AdminBO queryAdminInfoByMobile(String mobile) {
        if("".equals(mobile) || mobile == null){
            return null;
        }
        AdminBO adminBO = adminDAO.queryAdminInfoByMobile(mobile);
        // 查询用户所对应的权限
        List<MenuBO> menuBOS = adminDAO.getAdminToMenu(adminBO.getId());
        adminBO.setMenuBO(menuBOS);
        return adminBO;
    }

    /**
     * 管理员注册
     * @param admin
     * @return
     */
    public int adminRegister(AdminBO admin){
        return adminDAO.adminRegister(admin);
    }

    /**
     * 查找用户名是否注册过
     * @param name
     * @return
     */
    public int selectCountByName(String name){
        return adminDAO.selectCountByMobile(name);
    }

    /**
     * 查找手机号是否注册过
     * @param mobile
     * @return
     */
    public int selectCountByMobile(String mobile){
        return adminDAO.selectCountByMobile(mobile);
    }

    /**
     * 添加角色信息
     * @param roleBO
     * @return 返回受影响的行数
     */
    public int addRole(RoleBO roleBO){
        return adminDAO.addRole(roleBO);
    }

    /**
     * 查询所有的角色信息
     * @return  角色集合
     */
    public List<RoleBO> getRoleList(){
        return adminDAO.getRoleList();
    }

    /**
     * 查询所有的权限(菜单)信息
     * @return 权限集合
     */
    public List<MenuBO> getMenuList(){
        return adminDAO.getMenuList();
    }

    /**
     * 向角色权限表中添加数据
     * @param roleMenuBo
     * @return
     */
    public int addRoleMenu(RoleMenuBO roleMenuBo){
        return adminDAO.addRoleMenu(roleMenuBo);
    }

    /**
     * 根据用户id查询所对应的权限
     * @param userId 用户id
     * @return 返回权限信息
     */
    public List<MenuBO> getAdminToMenu(Integer userId){
        return adminDAO.getAdminToMenu(userId);
    }

    /**
     * 根据父id向权限表中添加数据
     * @param menuBO  添加的权限信息
     * @return 受影响的行数
     */
    public int addMenuByPid(MenuBO menuBO){
        adminDAO.addMenuByPid(menuBO);
        return 1;
    }

}
