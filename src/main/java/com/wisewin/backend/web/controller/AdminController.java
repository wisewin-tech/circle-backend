package com.wisewin.backend.web.controller;


import com.wisewin.backend.entity.dto.MenuDTO;
import com.wisewin.backend.entity.param.*;
import com.wisewin.backend.entity.bo.*;
import com.wisewin.backend.entity.bo.common.constants.SysConstants;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.AdminService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.MD5Util;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用戶信息控制类
 */
@Controller
@RequestMapping("/admin")
public class AdminController  extends BaseCotroller {
    @Resource
    private AdminService adminService ;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 管理员登录
     * @param request
     * @param response
     * @param mobile 手机号
     * @param password 密码
     */
    @RequestMapping("adminLogin")
    public void Login(HttpServletRequest request, HttpServletResponse response,String mobile,String password){
        /* 1. 验证参数是否完整 */
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！")) ;
            super.safeJsonPrint(response, result);
            return ;
        }
        //手机号和密码登录
        AdminBO userInfo = adminService.queryAdminInfoByMobile(mobile);
        if(userInfo == null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004" , "用户不存在！")) ;
            super.safeJsonPrint(response, result);
            return ;
        }
        // 判断密码是否正确
        if(!MD5Util.digest(password).equals(userInfo.getPassword())){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "密码输入不正确！")) ;
            super.safeJsonPrint(response, result);
            return ;
        }
        userInfo.setPassword("");
        // 登陆客户信息放入Redis缓存
        String uuid = UUID.randomUUID().toString();
        super.putLoginUser(uuid, userInfo);
        System.out.print(createKey(uuid, SysConstants.CURRENT_LOGIN_USER));
        super.setCookie(response, SysConstants.CURRENT_LOGIN_CLIENT_ID, uuid, 86400);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(userInfo)) ;
        super.safeJsonPrint(response, result);
        //验证码登录
    }

    /**
     * 管理员注册
     * @param request
     * @param response
     * @param param
     */
    @RequestMapping("adminRegister")
    public void register(HttpServletRequest request,HttpServletResponse response,RegisterParam param){
        // 判断是否为空
        if(param == null || StringUtils.isEmpty(param.getPassword()) || StringUtils.isEmpty(param.getGender())
                || StringUtils.isEmpty(param.getMobile()) || StringUtils.isEmpty(param.getName()) || StringUtils.isEmpty(String.valueOf(param.getRoleId()))){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            return ;
        }

        // 判断手机号是否注册过
        int count = adminService.selectCountByMobile(MD5Util.digest(param.getPassword()));
        if(count > 0 ){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002" , "手机号码已注册")) ;
            super.safeJsonPrint(response , result);
            return ;
        }

        // 判断用户名是否注册过
        int name = adminService.selectCountByName(param.getName());
        if(name > 0 ){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002" , "手机号码已注册")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
        AdminBO admin = new AdminBO();
        admin.setPassword(MD5Util.digest(param.getPassword()));
        admin.setName(param.getName());
        admin.setGender(param.getGender()); //
        admin.setPhoneNumber(param.getMobile());
        admin.setStatus(param.getStatus());// 状态 normal:正常  logout：注销
        admin.setCreateTime(new Date());
        admin.setRoleId(param.getRoleId());
        adminService.adminRegister(admin);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("")) ;
        super.safeJsonPrint(response, result);
    }

    /**
     * 添加角色
     * @param request
     * @param response
     * @param roleName  角色名称
     */
    @RequestMapping("addRole")
    public void addRole(HttpServletRequest request,HttpServletResponse response,String roleName){
        // 非空判断
        if(StringUtils.isEmpty(roleName)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
        RoleBO roleBO = new RoleBO();
        roleBO.setRoleName(roleName);
        roleBO.setCreateTime(new Date());
        roleBO.setUpdateTime(new Date());
        adminService.addRole(roleBO);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(roleBO.getId())) ;
        super.safeJsonPrint(response, result);
    }

    /**
     * 添加角色 就给角色赋予权限
     * @param request
     * @param response
     * @param roleName  角色名称
     * @param menuIds  权限ids
     */
    @RequestMapping("addRoleGrantAuthority")
    public void addRoleGrantAuthority(HttpServletRequest request,HttpServletResponse response,String roleName,String menuIds){
        // 非空判断
        if(StringUtils.isEmpty(roleName) || StringUtils.isEmpty(menuIds)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
        try {
            RoleBO roleBO = new RoleBO();
            roleBO.setRoleName(roleName);
            roleBO.setCreateTime(new Date());
            roleBO.setUpdateTime(new Date());
            // 添加角色
            adminService.addRole(roleBO);
            boolean status = menuIds.contains(",");
            if(status){
                String[] ids = menuIds.split(",");
                for (String id:
                        ids) {
                    RoleMenuBO roleMenuBO = new RoleMenuBO();
                    roleMenuBO.setRoleId(roleBO.getId());
                    roleMenuBO.setMenuId(Integer.parseInt(id));
                    roleMenuBO.setCreateTime(new Date());
                    roleMenuBO.setUpdateTime(new Date());
                    adminService.addRoleMenu(roleMenuBO);
                }
            }else{
                RoleMenuBO roleMenuBO = new RoleMenuBO();
                roleMenuBO.setRoleId(roleBO.getId());
                roleMenuBO.setMenuId(Integer.parseInt(menuIds));
                roleMenuBO.setCreateTime(new Date());
                roleMenuBO.setUpdateTime(new Date());
                adminService.addRoleMenu(roleMenuBO);
            }


            // 查询角色所拥有的权限
            List<MenuDTO> menuList = adminService.selectRoleToMenu(roleName);
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(menuList)) ;
            super.safeJsonPrint(response, result);
        }catch (Exception e){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加角色给角色赋予权限异常")) ;
            super.safeJsonPrint(response, result);
            e.printStackTrace();
        }
    }

    /**
     * 查询角色对应的权限
     * @param request
     * @param response
     * @param roleName
     */
    @RequestMapping("getRoleMenu")
    public void getRoleMenu(HttpServletRequest request,HttpServletResponse response,String roleName){
        if(StringUtils.isEmpty(roleName)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
        // 查询角色所拥有的权限
        List<MenuDTO> menuList = adminService.selectRoleToMenu(roleName);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(menuList)) ;
        super.safeJsonPrint(response, result);
    }

    /**
     * 给角色赋予权限
     * 首先获取到给哪个角色赋予权限(获取到角色的id),准备赋予哪个权限(获取到权限id),然后将信息保存到角色权限表
     * @param request
     * @param response
     * @param roleId  角色id
     * @param menuId  权限id
     */
    @RequestMapping("grantAuthority")
    public void grantAuthority(HttpServletRequest request,HttpServletResponse response,int roleId,int menuId){
        // 非空判断
        if(StringUtils.isEmpty(String.valueOf(roleId)) || StringUtils.isEmpty(String.valueOf(menuId))){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
        RoleMenuBO roleMenuBO = new RoleMenuBO();
        roleMenuBO.setRoleId(roleId);
        roleMenuBO.setMenuId(menuId);
        roleMenuBO.setCreateTime(new Date());
        roleMenuBO.setUpdateTime(new Date());
        adminService.addRoleMenu(roleMenuBO);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("")) ;
        super.safeJsonPrint(response, result);
    }

    /**
     * 查询所有角色
     * @return  所有角色信息
     */
    @RequestMapping("getRoleList")
    public void RoleList(HttpServletRequest request,HttpServletResponse response){
        List<RoleBO> roleList = adminService.getRoleList();
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(roleList)) ;
        super.safeJsonPrint(response, result);
    }

    /**
     * 查询所有权限(菜单)
     * @return 所有菜单
     */
    @RequestMapping("getMenuList")
    public void MenuList(HttpServletRequest request,HttpServletResponse response){
        List<MenuBO> menuList = adminService.getMenuList();
        // JsonUtils.getJONSArray4JavaList(roleList);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(menuList)) ;
        super.safeJsonPrint(response, result);
    }

    /**
     * 根据父id向权限表中添加菜单
     * @param request
     * @param response
     * @param menuParam
     */
    @RequestMapping("addMenu")
    public void addMenuByPid(HttpServletRequest request,HttpServletResponse response,MenuParam menuParam){
        // 判断是否为空
        if(menuParam == null || StringUtils.isEmpty(menuParam.getMenuName()) || StringUtils.isEmpty(menuParam.getUrl())
                ){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
        if( StringUtils.isEmpty(String.valueOf(menuParam.getPid()))){
            menuParam.setPid(0);
        }
        MenuBO menuBO = new MenuBO();
        menuBO.setMenuName(menuParam.getMenuName());
        menuBO.setPid(menuParam.getPid());
        menuBO.setStatus(menuParam.getStatus());
        menuBO.setUrl(menuParam.getUrl());
        menuBO.setCreateTime(new Date());
        menuBO.setUpdateTime(new Date());
        adminService.addMenuByPid(menuBO);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("")) ;
        super.safeJsonPrint(response, result);
    }

    /**
     * 模糊查询角色信息
     * @param request
     * @param response
     * @param dimName 模糊的名字
     */
    @RequestMapping("getDimRoleMenu")
    public void getDimRoleMenu(HttpServletRequest request,HttpServletResponse response,String dimName){
        if(StringUtils.isEmpty(dimName)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
        List<MenuDTO> menuList = adminService.getDimRoleMenu(dimName);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(menuList)) ;
        super.safeJsonPrint(response, result);
    }

    /**
     * 编辑角色的权限
     */
    public void editRoleMenu(){

    }

    public void delRoleMenu(HttpServletRequest request){

    }





    // 根据id查询菜单信息
    public void selectMenuById(HttpServletRequest request,HttpServletResponse response,Integer id){

    }

    // 根据id查询角色信息

    // 删除用户

    // 删除菜单信息

    // 删除角色信息




    @RequestMapping("test")
    public void getPublicSession(HttpServletRequest request, HttpServletResponse response,String key){
        System.out.println(super.getLoginUser(request));
        AdminBO result = (AdminBO) super.getLoginUser(request);
        String re = JsonUtils.getJsonString4JavaPOJO(result);
        super.safeJsonPrint(response, re);
    }


    public static void main(String[] args) {
//        System.out.println(MD5Util.digest("123456"));
//        System.out.println(MD5Util.digest("456789"));
//        System.out.println(MD5Util.digest("zhang"));
//        System.out.println(MD5Util.digest("123456789"));

        String str = "1,2,3,4,5,6,7,8,9,10";
        String [] strs =  str.split(",");
        for (String s:
             strs) {
            // System.out.println(s);
        }
        //System.out.println(strs);

        try{
            int a =  1/0;
            System.out.println("bbb");
            System.out.println(1+1);

        }catch (Exception e){
            System.out.println("出现异常了");
        }
    }
}
