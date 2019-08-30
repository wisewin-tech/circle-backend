package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.TheGarageImgBO;
import com.wisewin.backend.entity.bo.UserBO;
import com.wisewin.backend.entity.dto.BackgroundCountDTO;
import com.wisewin.backend.entity.dto.GarageImgDTO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.UserParam;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.UserService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseCotroller{

    @Resource
    private UserService userService;

    /**
     * 查询所有用户信息
     * pageNo 页数
     * pageSize 每页条数
     * @param response
     */
    @RequestMapping("/selectAll")
    public void selectAll(UserParam userParam,HttpServletRequest request,HttpServletResponse response) {
        AdminBO loginUser = super.getLoginUser(request);
        if(loginUser == null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        QueryInfo queryInfo = getQueryInfo(userParam.getPageNo(),userParam.getPageSize());
        Map<String, Object> queryMap = new HashMap<String, Object>();
        if(queryInfo != null){
            queryMap.put("pageOffset", queryInfo.getPageOffset());
            queryMap.put("pageSize", queryInfo.getPageSize());
        }
        queryMap.put("id",userParam.getId());
        queryMap.put("name",userParam.getName());
        queryMap.put("phoneNumber",userParam.getPhoneNumber());
        queryMap.put("gender",userParam.getGender());
        queryMap.put("authenticationStatus",userParam.getAuthenticationStatus());
        //把带有条件的查询结果集放入map中
        List<UserBO> userBOS= userService.selectAll(queryMap);
        Integer count = userService.selectCount(queryMap);
        Map<String,Object>  resultMap = new HashMap<String, Object>();
        resultMap.put("UserBOList",userBOS);
        resultMap.put("count",count);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));
        super.safeJsonPrint(response, json);
        return;
    }

    /**
     * 修改用户信息
     */
    @RequestMapping("/update")
    public void updateUser(HttpServletRequest request,HttpServletResponse response,UserParam userParam) {
        AdminBO loginUser = super.getLoginUser(request);
        if(loginUser == null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        //如果id或者参数为空,提示参数异常
        if (userParam.getId()==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
            super.safeJsonPrint(response, result);
            return ;
        }
        Integer i = userService.updateUser(userParam);
        if (i>0){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功！")) ;
            super.safeJsonPrint(response, result);
            return;
        }else {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure( "修改失败！")) ;
            super.safeJsonPrint(response, result);
            return ;
        }
    }

    /**
     * 冻结用户
     * @param request
     * @param response
     */
    @RequestMapping("/userFrozen")
    public void updateAccountStatus(HttpServletRequest request, HttpServletResponse response, Integer userId){
        AdminBO loginUser = super.getLoginUser(request);
        if(loginUser == null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        userService.updateAccountStatus(userId);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("操作成功！")) ;
        super.safeJsonPrint(response, result);
        return;

    }

    /**
     * 用户背景图审核
     * @param request
     * @param response
     */
    @RequestMapping("/queryBackGround")
    public void userBackGround(HttpServletRequest request, HttpServletResponse response, Integer userId,
                               String pattern, String state, Integer pageOffset, Integer pageSize){
        AdminBO loginUser = super.getLoginUser(request);
        if(loginUser == null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        QueryInfo queryInfo = getQueryInfo(pageOffset, pageSize);
        //创建一个用于封装sql条件的map集合
        Map<String, Object> condition = new HashMap<String, Object>();
        if (queryInfo != null) {
            //把pageOffset 页数,pageSize每页的条数放入map集合中
            condition.put("pageOffset", queryInfo.getPageOffset());
            condition.put("pageSize", queryInfo.getPageSize());
        }
        condition.put("userId", userId);
        condition.put("pattern", pattern);
        condition.put("state", state);
        BackgroundCountDTO backgroundCountDTO = userService.queryBackground(condition);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(backgroundCountDTO)) ;
        super.safeJsonPrint(response, result);
        return;
    }

    /**
     * 车辆审核
     * @param request
     * @param response
     * @param pageOffset
     * @param pageSize
     */
    @RequestMapping("/queryGarage")
    public void queryGarage(HttpServletRequest request, HttpServletResponse response, Integer pageOffset, Integer pageSize,
                            Integer userId, String plateNumber, String branModel,
                            String status, String headingCode){
        AdminBO loginUser = super.getLoginUser(request);
        if(loginUser == null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        QueryInfo queryInfo = getQueryInfo(pageOffset, pageSize);
        //创建一个用于封装sql条件的map集合
        Map<String, Object> condition = new HashMap<String, Object>();
        if (queryInfo != null) {
            //把pageOffset 页数,pageSize每页的条数放入map集合中
            condition.put("pageOffset", queryInfo.getPageOffset());
            condition.put("pageSize", queryInfo.getPageSize());
        }
        condition.put("userId", userId);
        condition.put("plateNumber", plateNumber);
        condition.put("branModel", branModel);
        condition.put("status", status);
        condition.put("headingCode", headingCode);
        GarageImgDTO garageImgDTO = userService.queryGarage(condition);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(garageImgDTO)) ;
        super.safeJsonPrint(response, result);
        return;
    }

    /**
     * 获取车辆图片
     * @param request
     * @param response
     * @param id
     */
    @RequestMapping("/queryGarageImg")
    public void queryGarageImg(HttpServletRequest request, HttpServletResponse response, Integer id){
        AdminBO loginUser = super.getLoginUser(request);
        if(loginUser == null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        List<TheGarageImgBO> theGarageImgBOS = userService.queryGarageImg(id);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(theGarageImgBOS)) ;
        super.safeJsonPrint(response, result);
        return;
    }
}
