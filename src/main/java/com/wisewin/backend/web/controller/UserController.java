package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.*;
import com.wisewin.backend.entity.dto.BackgroundCountDTO;
import com.wisewin.backend.entity.dto.GarageImgDTO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.UserParam;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.UserService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseCotroller {

    @Resource
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    /**
     * 查询所有用户信息
     * pageNo 页数
     * pageSize 每页条数
     * certificationStatus 用户认证状态
     * carStatus 汽车认证状态
     * userStatus 用户状态
     * robotStatus 是否为机器人
     * phone 手机号
     *
     * @param response
     */
    @RequestMapping("/getUserList")
    public void getUserList(UserBO userBO, Integer pageNo, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
        //验证参数
        if (userBO == null) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            return;
        }
        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);
        //封装条件
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("pageOffset", queryInfo.getPageOffset());
        queryMap.put("pageSize", queryInfo.getPageSize());
        queryMap.put("certificationStatus", userBO.getCertificationStatus());
        queryMap.put("carStatus", userBO.getCarStatus());
        queryMap.put("userStatus", userBO.getUserStatus());
        queryMap.put("robotStatus", userBO.getRobotStatus());
        queryMap.put("phone", userBO.getPhone());
        //查询
        Integer count = userService.getUserListCount(queryMap);
        List<UserBO> userBOS = new ArrayList<UserBO>();
        if (count != 0) {
            userBOS = userService.getUserList(queryMap);
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("userBOS", userBOS);
        resultMap.put("count", count);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));
        super.safeJsonPrint(response, json);
        return;
    }

    /**
     * 查询用户详细信息（模式信息、背景图片、兴趣）
     */
    @RequestMapping("/getModelByUserId")
    public void getModelByUserId(Long userId, HttpServletRequest request, HttpServletResponse response) {
        //验证参数
        if (userId == null) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(userService.getModelByUserId(userId)));
        super.safeJsonPrint(response, json);
    }

    /**
     * 修改用户状态（拉黑，取消拉黑）
     */
    @RequestMapping("/updUserStatus")
    public void updUserStatus(HttpServletRequest request, HttpServletResponse response, Long userId, String status) {
        if (userId == null || status == null) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            return;
        }
        UserParam userParam=new UserParam();
        userParam.setId(userId);
        userParam.setUserStatus(status);
        userService.updateUser(userParam);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("0000000"));
        super.safeJsonPrint(response, result);
        return;
    }

    /**
     * 查询用户认证列表
     */
    @RequestMapping("/getUserCertification")
    public void getUserCertification(HttpServletRequest request, HttpServletResponse response,  String status) {
        if (status == null) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            return;
        }
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(userService.getUserCertification(status)));
        super.safeJsonPrint(response, result);
        return;
    }

    /**
     * 修改用户认证状态（通过，不通过）
     */
    @RequestMapping("/updUserCertificationStatus")
    public void updUserCertificationStatus(HttpServletRequest request, HttpServletResponse response, UserCertificationBO userCertificationBO) {
        if (userCertificationBO == null) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            return;
        }
        AdminBO adminBO=super.getLoginUser(request);
        userCertificationBO.setAdminId(adminBO.getId());
        userService.updUserCertificationStatus(userCertificationBO);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("0000000"));
        super.safeJsonPrint(response, result);
        return;
    }

    /**
     * 添加机器人
     */
    @RequestMapping("/addRobotUser")
    public void addRobotUser(@RequestBody UserBO userParam, HttpServletRequest request, HttpServletResponse response){
        if (userParam == null) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            return;
        }
        userService.addRobotUser(userParam);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("0000000"));
        super.safeJsonPrint(response, result);
        return;
    }

    /**
     * 修改机器人信息
     */
    @RequestMapping("/updRobotUser")
    public void updRobotUser(UserBO userParam,HttpServletRequest request, HttpServletResponse response){
        if (userParam == null) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            return;
        }
        userService.updRobotUser(userParam);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("0000000"));
        super.safeJsonPrint(response, result);
        return;
    }

}
