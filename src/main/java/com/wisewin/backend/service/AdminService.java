package com.wisewin.backend.service;


import com.wisewin.backend.dao.AdminDAO;
import com.wisewin.backend.entity.bo.*;
import com.wisewin.backend.entity.dto.AdminDTO;
import com.wisewin.backend.entity.dto.AdminRoleDTO;
import com.wisewin.backend.entity.dto.MenuDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
        if(adminBO != null){
            // List<MenuBO> menuBOS = adminDAO.getAdminToMenu(adminBO.getId());
            List<RoleBO> roleBOS = adminDAO.getRoleMenuSuccess(adminBO.getRoleId());
            adminBO.setRoleBO(roleBOS);
            return adminBO;
        }
        return null;
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
     * 判断角色名称是否注册过
     * @param roleName
     * @return
     */
    public Integer selectCountByRoleName(String roleName){
        return adminDAO.selectCountByRoleName(roleName);
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
        return adminDAO.addMenuByPid(menuBO);
    }

    /**
     * 根据id查询权限表(菜单信息)
     * @param id  权限id
     * @return 菜单信息
     */

    /**
     * 根据角色名查询拥有的权限
     * @param map
     * @return 返回对应的权限
     */
    public List<RoleBO> selectRoleToMenu(Map<String,Object> map){
        return adminDAO.selectRoleToMenu(map);
    }

    /**
     * 根据角色名称查找对应的权限总数
     * @param map
     * @return
     */
    public Integer getCountRoleToMenu(Map<String,Object> map){
        return adminDAO.getCountRoleToMenu(map);
    }

    /**
     * 根据角色id查询拥有的权限
     * @param roleId  角色名
     * @return 返回对应的权限
     */
    public List<RoleBO> selectRoleMenuById(Integer roleId){
        return adminDAO.selectRoleMenuById(roleId);
    }

    /**
     * 根据角色名称查询对应的权限(模糊查询)
     * @param dimName 模糊查询的名字
     * @return 查询的权限信息
     */
    public List<MenuDTO> getDimRoleMenu(String dimName){
        return adminDAO.getDimRoleMenu(dimName);
    }

    /**
     * 根据角色id删除角色
     * @param roleId 角色id
     */
    public void delRoleById(Integer roleId){
        adminDAO.delRoleById(roleId);
    }

    /**
     * 查询所有角色的权限
     * @return
     */
    public List<MenuDTO> getRoleMenu(String roleName){
        return adminDAO.getRoleMenu(roleName);
    }

    /**
     * 根据用户名查询对应的角色
     * @param userName
     * @return
     */
    public List<AdminRoleDTO> getAdminRoleByName(String userName){
        return adminDAO.getAdminRoleByName(userName);
    }

    /**
     * 根据用户id修改角色id
     * @param roleId 角色id
     * @param id  用户id
     * @return
     */
    public boolean editUserRole(Integer roleId,Integer id){
        return adminDAO.editUserRole(roleId, id);
    }

    /**
     * 根据用户id删除用户信息
     * @param id
     * @return
     */
    public boolean delAdminById(Integer id){
        return adminDAO.delAdminById(id);
    }

    /**
     * 修改admin用户信息
     * @param adminBO 用户信息
     * @return 是否修改成功
     */
    public boolean updateAdminUser(AdminBO adminBO){
        return adminDAO.updateAdminUser(adminBO);
    }

    /**
     * 查询admin用户信息
     * @param adminBO
     * @return
     */
    public List<AdminDTO> getAdmin(AdminBO adminBO){
        return adminDAO.getAdmin(adminBO);
    }

    /**
     * 根据角色名称查找对应的角色id
     * @param roleName
     * @return
     */
    public Integer getRoleIdByRoleName(String roleName){
        return adminDAO.getRoleIdByRoleName(roleName);
    }

    /**
     * 删除某个角色对应的id
     * @param roleId 角色id
     * @param menuId 权限id
     * @return 是否删除成功
     */
    public boolean delRoleMenu(Integer roleId,Integer menuId){
        return adminDAO.delRoleMenu(roleId,menuId);
    }

    /**
     * 根据角色id删除对应的权限
     * @param roleId
     */
    public void delRoleMenuByRoleId(Integer roleId){
        adminDAO.delRoleMenuByRoleId(roleId);
    }

    /**
     * 根据角色id修改角色名称
     * @param roleId 角色id
     * @param roleName 角色名称
     */
    public void updateRoleNameByRoleId(Integer roleId,String roleName){
        adminDAO.updateRoleNameByRoleId(roleId,roleName);
    }

    // =======================测试
    public List<RoleBO> getRole(String roleName){
        return adminDAO.getRole(roleName);
    }

    public RoleBO test(){
        return adminDAO.test();
    }

    public List<RoleBO> getRoleMenuSuccess(Integer roleId){
        return adminDAO.getRoleMenuSuccess(roleId);
    }


}
