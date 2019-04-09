package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.*;
import com.wisewin.backend.entity.dto.AdminRoleDTO;
import com.wisewin.backend.entity.dto.MenuDTO;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
     * 查找用户手机号是否注册过
     * @param name
     * @return
     */
    int selectCountByName(String name);

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

    /**
     * 根据角色名称查找对应的权限
     * @param map 角色名称
     * @return 返回对应的权限
     */
    List<MenuDTO> selectRoleToMenu(Map<String,Object> map);

    /**
     * 根据角色名称查找对应的权限总数
     * @param map
     * @return
     */
    Integer getCountRoleToMenu(Map<String,Object> map);

    /**
     * 根据角色名称查找对应的权限(模糊查询)
     * @param dimName 角色名称
     * @return 返回对应的权限
     */
    List<MenuDTO> getDimRoleMenu(String dimName);

    /**
     * 根据角色名称查找对应的权限(模糊查询)
     * @param roleId 角色名称
     * @return 返回对应的权限
     */
    List<MenuDTO> selectRoleMenuById(Integer roleId);

    /**
     * 根据角色id删除角色信息
     * @param roleId 角色id
     */
    void delRoleById(Integer roleId);

    /**
     * 查询所有角色对应的权限
     * @return
     */
    List<MenuDTO> getRoleMenu(String roleName);

    // 根据pid查询子菜单
    List<MenuBO> getCh(Integer pid);

    /**
     * 根据用户名查询对应的角色
     * @param userName
     * @return
     */
    List<AdminRoleDTO> getAdminRoleByName(String userName);

    /**
     * 根据用户id修改角色id
     * @param roleId 角色id
     * @param id  用户id
     * @return
     */
    boolean editUserRole(@Param("roleId")Integer roleId,@Param("id")Integer id );

    /**
     * 根据用户id删除用户信息
     * @param id 用户id
     * @return
     */
    boolean delAdminById(Integer id);

    /**
     * 修改admin用户信息
     * @param adminBO 修改信息
     * @return 是否修改成功
     */
     boolean updateAdminUser(AdminBO adminBO);

    /**
     * 查询用户信息
     * @param adminBO
     * @return
     */
     List<AdminBO> getAdmin(AdminBO adminBO);


    List<RoleBO> getRole();


}
