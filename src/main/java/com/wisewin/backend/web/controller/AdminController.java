package com.wisewin.backend.web.controller;


import com.wisewin.backend.common.constants.CircleConstants;
import com.wisewin.backend.entity.dto.*;
import com.wisewin.backend.entity.param.*;
import com.wisewin.backend.entity.bo.*;
import com.wisewin.backend.entity.bo.common.constants.SysConstants;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.AdminService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.MD5Util;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @RequestMapping("/adminLogin")
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
    @RequestMapping("/adminRegister")
    public void register(HttpServletRequest request,HttpServletResponse response,RegisterParam param){
        // 判断是否为空
        if(param == null || StringUtils.isEmpty(param.getPassword()) || StringUtils.isEmpty(param.getGender())
                || StringUtils.isEmpty(param.getMobile()) || StringUtils.isEmpty(param.getName()) || StringUtils.isEmpty(String.valueOf(param.getRoleId()))){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            return ;
        }

        // 判断手机号是否注册过
        int count = adminService.selectCountByMobile(param.getMobile());
        if(count > 0 ){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002" , "手机号码已注册")) ;
            super.safeJsonPrint(response , result);
            return ;
        }

        // 判断用户名是否注册过
        int name = adminService.selectCountByName(param.getName());
        if(name > 0 ){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002" , "用户名已存在")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
        AdminBO admin = new AdminBO();
        admin.setPassword(MD5Util.digest(param.getPassword()));
        admin.setName(param.getName());
        admin.setGender(param.getGender()); //
        admin.setPhoneNumber(param.getMobile());
        admin.setStatus(CircleConstants.NORMAL);// 状态 normal:正常  logout：注销
        admin.setCreateTime(new Date());
        admin.setRoleId(param.getRoleId());
        admin.setEmail(param.getEmail());
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
    @RequestMapping("/addRole")
    public void addRole(HttpServletRequest request,HttpServletResponse response,String roleName){
        // 非空判断
        if(StringUtils.isEmpty(roleName)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
        // 判断角色名称是否存在
        Integer count = adminService.selectCountByRoleName(roleName);
        if(count>0){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002" , "该角色已存在")) ;
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
    @RequestMapping("/addRoleGrantAuthority")
    public void addRoleGrantAuthority(HttpServletRequest request,HttpServletResponse response,String roleName,String menuIds,Integer pageNo, Integer pageSize){
        // 非空判断
        if(StringUtils.isEmpty(roleName) || StringUtils.isEmpty(menuIds)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
        // 判断角色名称是否存在
        Integer nameCount = adminService.selectCountByRoleName(roleName);
        if(nameCount>0){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002" , "该角色已存在")) ;
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
                    // 添加权限
                    adminService.addRoleMenu(roleMenuBO);
                }
            }else{
                RoleMenuBO roleMenuBO = new RoleMenuBO();
                roleMenuBO.setRoleId(roleBO.getId());
                roleMenuBO.setMenuId(Integer.parseInt(menuIds));
                roleMenuBO.setCreateTime(new Date());
                roleMenuBO.setUpdateTime(new Date());
                // 添加权限
                adminService.addRoleMenu(roleMenuBO);
            }
            QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);
            Map<String, Object> map = new HashMap<String, Object>();
            if  (queryInfo != null) {
                map.put("pageOffset", queryInfo.getPageOffset());
                map.put("pageSize", queryInfo.getPageSize());

            }
            map.put("roleName", roleName);
            // 查询总记录数
            Integer count = adminService.getCountRoleToMenu(map);
            // 查询角色所拥有的权限
            List<RoleBO> menuList = adminService.selectRoleToMenu(map);
            List<RoleDTO> roleDTOS = new ArrayList<RoleDTO>();
            for (RoleBO ro:menuList) {
                RoleDTO roleDTO = new RoleDTO();
                List<Integer> menuId = new ArrayList<Integer>();// 存放权限id
                List<String> menuName = new ArrayList<String>(); // 存放权限name
                roleDTO.setId(ro.getId());// 角色id
                roleDTO.setRoleName(ro.getRoleName()); // 角色名称
                roleDTO.setCreateTime(ro.getCreateTime());
                roleDTO.setUpdateTime(ro.getUpdateTime());
                List<MenuBO> menus = ro.getMenuBOS();// 角色对应的权限id
                for (int i=0;i<menus.size();i++ ) {
                    menuId.add(menus.get(i).getId());
                    menuName.add(menus.get(i).getMenuName());
                }
                boolean flag = menuIds.contains(",");
                if(flag) {
                    String[] ids = menuIds.split(",");
                    for (String id :
                            ids) {
                        menuId.add(Integer.parseInt(id));
                    }
                }
                roleDTO.setMenuIds(menuId);
                roleDTO.setMenuNames(menuName);
                roleDTOS.add(roleDTO);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("count", count);
            jsonObject.put("data", roleDTOS);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(jsonObject));
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(json)) ;
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
    public void getRoleMenu(HttpServletRequest request,HttpServletResponse response,String roleName, Integer pageNo, Integer pageSize){
        if(StringUtils.isEmpty(roleName)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        if  (queryInfo != null) {
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());

        }
        map.put("roleName", roleName);
        // 查询总记录数
        Integer count = adminService.getCountRoleToMenu(map);
        // 查询角色所拥有的权限
        List<RoleBO> menuList = adminService.selectRoleToMenu(map);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count", count);
        jsonObject.put("data", menuList);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(jsonObject));
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(json)) ;
        super.safeJsonPrint(response, result);
    }

    /**
     * 编辑对应角色的权限
     * 首先获取到给哪个角色赋予权限(获取到角色的id),准备赋予哪个权限(获取到权限id),然后将信息保存到角色权限表
     * @param request
     * @param response
     * @param roleId  角色id
     * @param menuIds  权限id
     */
    @RequestMapping("grantAuthority")
    public void grantAuthority(HttpServletRequest request,HttpServletResponse response,Integer roleId,String menuIds,String roleName){
        // 非空判断
        if(StringUtils.isEmpty(String.valueOf(roleId)) || StringUtils.isEmpty(String.valueOf(menuIds))){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
        if(!StringUtils.isEmpty(roleName)){
            // 判断角色名称是否存在
            Integer nameCount = adminService.selectCountByRoleName(roleName);
            if(nameCount>0){
                String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002" , "该角色已存在")) ;
                super.safeJsonPrint(response , result);
                return ;
            }
            adminService.updateRoleNameByRoleId(roleId,roleName,new Date());
        }
        // 根据角色id删除对应的权限
        adminService.delRoleMenuByRoleId(roleId);
        boolean status = menuIds.contains(",");
        if(status){
            String[] ids = menuIds.split(",");
            for (String id:
                    ids) {
                RoleMenuBO roleMenuBO = new RoleMenuBO();
                roleMenuBO.setRoleId(roleId);
                roleMenuBO.setMenuId(Integer.parseInt(id));
                // roleMenuBO.setCreateTime(new Date());
                roleMenuBO.setUpdateTime(new Date());
                // 添加权限
                adminService.addRoleMenu(roleMenuBO);
            }
        }else{
            RoleMenuBO roleMenuBO = new RoleMenuBO();
            roleMenuBO.setRoleId(roleId);
            roleMenuBO.setMenuId(Integer.parseInt(menuIds));
            // roleMenuBO.setCreateTime(new Date());
            roleMenuBO.setUpdateTime(new Date());
            // 添加权限
            adminService.addRoleMenu(roleMenuBO);
        }
        // 查询角色所拥有的权限
        List<RoleDTO> roleDTOS = new ArrayList<RoleDTO>();
        List<RoleBO> menuList = adminService.selectRoleMenuById(roleId);
        for (RoleBO ro:menuList) {
            RoleDTO roleDTO = new RoleDTO();
            List<Integer> menuId = new ArrayList<Integer>();// 存放权限id
            List<String> menuName = new ArrayList<String>(); // 存放权限name
            roleDTO.setId(ro.getId());// 角色id
            roleDTO.setRoleName(ro.getRoleName()); // 角色名称
            roleDTO.setCreateTime(ro.getCreateTime());
            roleDTO.setUpdateTime(ro.getUpdateTime());
            List<MenuBO> menus = ro.getMenuBOS();// 角色对应的权限id
            for (int i=0;i<menus.size();i++ ) {
                menuName.add(menus.get(i).getMenuName());
            }
            boolean flag = menuIds.contains(",");
            if(flag) {
                String[] ids = menuIds.split(",");
                for (String id :
                        ids) {
                    menuId.add(Integer.parseInt(id));
                }
            }
            roleDTO.setMenuIds(menuId);
            roleDTO.setMenuNames(menuName);
            roleDTOS.add(roleDTO);
        }
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(roleDTOS)) ;
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
     * 删除角色信息 根据角色id
     * @param request
     * @param roleIds
     */
    @RequestMapping("delRoleByIds")
    public void delRoleByIds(HttpServletRequest request,HttpServletResponse response,String roleIds){
        // 非空判断
        if(StringUtils.isEmpty(String.valueOf(roleIds))){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
        boolean status = roleIds.contains(",");
        if(status){
            String [] ids = roleIds.split(",");
            for (String id:ids ) {
                adminService.delRoleById(Integer.parseInt(id));
            }
        }else{
            adminService.delRoleById(Integer.parseInt(roleIds));
        }
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除成功")) ;
        super.safeJsonPrint(response, result);
    }

    /** success
     * 根据角色名称查询角色对应的权限(如果传的角色名是空，则查询所有角色对应的权限)
     * @param request
     * @param response
     */
    @RequestMapping("/getAllRoleMenu")
    public void getAllRoleMenu(HttpServletRequest request,HttpServletResponse response,String roleName,Integer pageNo, Integer pageSize){
        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        if  (queryInfo != null) {
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());

        }
        map.put("roleName", roleName);
        // 查询总记录数
        //Integer count = adminService.getCountRoleToMenu(map);
        // Integer count = adminService.getCountRole(map);
        // 查询角色所拥有的权限
        // List<RoleBO> menuList = adminService.selectRoleToMenu(map);

        List<RoleBO> ros = adminService.getRole(roleName);
        List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
        if(ros == null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "没有查到相关记录")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
        for (RoleBO ro:ros) {
            RoleDTO roleDTO = new RoleDTO();
            List<Integer> menuIds = new ArrayList<Integer>();// 存放权限id
            List<String> menuName = new ArrayList<String>(); // 存放权限name
            roleDTO.setId(ro.getId());// 角色id
            roleDTO.setRoleName(ro.getRoleName()); // 角色名称
            roleDTO.setCreateTime(ro.getCreateTime());
            roleDTO.setUpdateTime(ro.getUpdateTime());
            List<MenuBO> menus = ro.getMenuBOS();// 角色对应的权限id
            for (int i=0;i<menus.size();i++ ) {
                menuIds.add(menus.get(i).getId());
                menuName.add(menus.get(i).getMenuName());
            }
            roleDTO.setMenuIds(menuIds);
            roleDTO.setMenuNames(menuName);
            roleDTOs.add(roleDTO);
        }
        /*JSONObject jsonObject = new JSONObject();
        jsonObject.put("count", count);
        jsonObject.put("data", roleDTOs);*/
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(roleDTOs));
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(json)) ;
        super.safeJsonPrint(response, result);
    }

    @RequestMapping("getRoleTest")
    public void Test(HttpServletRequest request,HttpServletResponse response,String roleName){
        if(StringUtils.isEmpty(roleName)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
//        List<RoleBO> list = adminService.getRole(new Map<>);
//        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(list)) ;
//        super.safeJsonPrint(response, result);
    }

    /** faild
     * 查询用户所对应的角色
     * @param request
     * @param response
     * @param userName 用戶名
     */
    @RequestMapping("getAdminRoleByName")
    public void getAdminRoleByName(HttpServletRequest request,HttpServletResponse response,String userName,Integer pageNo, Integer pageSize){

        // 查询用户和对应的角色
        List<AdminRoleDTO> adminRoleDTOS = adminService.getAdminRoleByName(userName);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(adminRoleDTOS)) ;
        super.safeJsonPrint(response, result);
    }

    /**
     * 编辑用户所对应的角色
     * @param request
     * @param response
     * @param id 用戶id
     * @param roleId 角色id
     */
    @RequestMapping("editUserRole")
    public void editUserRole(HttpServletRequest request,HttpServletResponse response,Integer id,Integer roleId){
        // 非空判断
        if(StringUtils.isEmpty(String.valueOf(id))|| StringUtils.isEmpty(String.valueOf(roleId))){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
        boolean boo = adminService.editUserRole(roleId, id);
        if(boo){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "修改成功")) ;
            super.safeJsonPrint(response , result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "修改有误")) ;
            super.safeJsonPrint(response , result);
        }
    }

    /**
     * 修改admin用戶信息
     * @param request
     * @param response
     * @param param
     * @param
     */
    @RequestMapping("updateAdminUser")
    public void updateAdminUser(HttpServletRequest request,HttpServletResponse response,RegisterParam param){
        if(param == null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
        // 判断手机号是否注册过
        if(!StringUtils.isEmpty(param.getMobile())){
            int count = adminService.selectCountByMobile(param.getMobile());
            if(count > 0 ){
                String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002" , "手机号码已注册")) ;
                super.safeJsonPrint(response , result);
                return ;
            }
        }


        // 判断用户名是否注册过
        if(!StringUtils.isEmpty(param.getName())){
            int name = adminService.selectCountByName(param.getName());
            if(name > 0 ){
                String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002" , "用户名已存在")) ;
                super.safeJsonPrint(response , result);
                return ;
            }
        }
//        AdminBO adminBO = new AdminBO();
//        adminBO.setId(param.getId());
        Map map = new HashMap();
        map.put("id",param.getId());
        List<AdminDTO> adminBOS = adminService.getAdmin(map);
        for (AdminDTO adminDTO:adminBOS
             ) {
            adminDTO.setId(param.getId());
            adminDTO.setGender(param.getGender());
            adminDTO.setPassword(param.getPassword());
            adminDTO.setPhoneNumber(param.getMobile());
            adminDTO.setName(param.getName());
            adminDTO.setEmail(param.getEmail());
            adminDTO.setUpdateTime(new Date());
            AdminBO admin = new AdminBO();
            admin.setId(adminDTO.getId());
            admin.setGender(adminDTO.getGender());
            admin.setPassword(adminDTO.getPassword());
            admin.setPhoneNumber(adminDTO.getPhoneNumber());
            admin.setName(adminDTO.getName());
            admin.setEmail(adminDTO.getEmail());
            admin.setRoleId(param.getRoleId());
            admin.setUpdateTime(new Date());
            boolean flag = adminService.updateAdminUser(admin);
            if(flag){
                String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功")) ;
                super.safeJsonPrint(response , result);
                return ;
            }else{
                String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
                super.safeJsonPrint(response , result);
                return ;
            }
        }
    }

    /**
     * 查询用户信息
     * @param request
     * @param response
     * @param adminParam
     */
    @RequestMapping("getAdmin")
    public void getAdmin(HttpServletRequest request,HttpServletResponse response,GetAdminParam adminParam,Integer pageNo,Integer pageSize){
        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        if  (queryInfo != null) {
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());

        }

        if(!StringUtils.isEmpty(adminParam.getMobile())){
            map.put("mobile",adminParam.getMobile());
        }
        if(!StringUtils.isEmpty(adminParam.getName())){
            map.put("name", adminParam.getName());
        }
        if(!StringUtils.isEmpty(String.valueOf(adminParam.getId()))){
            map.put("id", adminParam.getId());
        }
        if(!StringUtils.isEmpty(String.valueOf(adminParam.getRoleId()))){
            map.put("roleId", adminParam.getRoleId());
        }
        if(!StringUtils.isEmpty(adminParam.getEmail())){
            map.put("email", adminParam.getEmail());
        }
        if(!StringUtils.isEmpty(adminParam.getRoleName())){
            map.put("roleName", adminParam.getRoleName());
        }
        // 查询总记录数
        Integer count = adminService.getAdminCountByMap(map);
        // 根据map中的数据查询用户信息
        List<AdminDTO> adminBOS = adminService.getAdmin(map);
        for (int i=0;i<adminBOS.size();i++
             ) {
            adminBOS.get(i).setPassword("");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count", count);
        jsonObject.put("data", adminBOS);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(jsonObject));
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(json)) ;
        super.safeJsonPrint(response, result);
    }

    /**
     * 删除某个角色对应的权限
     * @param roleId 角色id
     * @param menuIds 权限id
     * @return 是否删除成功
     */
    @RequestMapping("delRoleMenu")
    public void delRoleMenu(HttpServletRequest request,HttpServletResponse response,Integer roleId,String menuIds){
        if(roleId==null || StringUtils.isEmpty(menuIds)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
        boolean flag = menuIds.contains(",");
        if(flag){
            String [] ids = menuIds.split(",");
            for (String id:ids ) {
                adminService.delRoleMenu(roleId,Integer.parseInt(id));
            }
        }else{
            adminService.delRoleMenu(roleId,Integer.parseInt(menuIds));
        }
        // 查询角色所拥有的权限
        List<RoleBO> menuList = adminService.selectRoleMenuById(roleId);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(menuList)) ;
        super.safeJsonPrint(response, result);
    }

    /**
     * 删除用户
     * @param request
     * @param response
     * @param ids 需要刪除的用戶的id
     */
    @RequestMapping("delAdmin")
    public void delAdmin(HttpServletRequest request,HttpServletResponse response,String ids){
        // 非空判断
        if(StringUtils.isEmpty(ids)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
        boolean status = ids.contains(",");
        if(status){
            String [] id = ids.split(",");
            for (String idd:
                 id) {
                adminService.delAdminById(Integer.parseInt(idd));
            }
        }else{
            adminService.delAdminById(Integer.parseInt(ids));
        }
    }


    /**
     * 根据角色id查询对应的权限
     * @param request
     * @param response
     */
    @RequestMapping("test")
    public void getPublicSession(HttpServletRequest request, HttpServletResponse response){
        List<RoleBO> ro = adminService.getRoleMenuSuccess(0);
        // sqlSession.selectList("findAll",null,new RowBounds(0,2));
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(ro)) ;
        super.safeJsonPrint(response, result);
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
