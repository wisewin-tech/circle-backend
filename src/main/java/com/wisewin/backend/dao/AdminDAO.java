package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.*;

import java.util.List;

public interface AdminDAO {
    /**
     * 通过手机号查找管理员信息
     * @param mobile
     * @return UserDO
     */
    AdminBO queryAdminInfoByMobile(String mobile);

    /**
     * 注册管理员信息
     * @param admin
     * @return
     */
    int adminRegister(AdminBO admin);

    /**
     * 查找用户手机号是否注册过
     * @param mobile
     * @return
     */
    int selectCountByMobile(String mobile);

    /**
     * 添加角色信息
     * @param roleBO
     * @return 返回受影响的条数(添加成功几条数据)
     */
    int addRole(RoleBO roleBO);

    /**
     * 查询所有角色
     * @return 所有角色
     */
    List<RoleBO> getRoleList();

    /**
     * 查詢所有权限(菜单)
     * @return  所有权限
     */
    List<MenuBO> getMenuList();

    /**
     * 向角色权限表中添加数据
     * @param roleMenuBO
     * @return
     */
    int addRoleMenu(RoleMenuBO roleMenuBO);

    /**
     * 根据用户id查询所对应的权限
     * @param userId
     * @return 返回权限信息
     */
    List<MenuBO> getAdminToMenu(Integer userId);

    /**
     * 根据父id向权限表中添加数据
     * @param menuBO 添加的菜单信息
     * @return 受影响的行数
     */
    int addMenuByPid(MenuBO menuBO);
}
