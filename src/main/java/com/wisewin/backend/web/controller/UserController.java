package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.UserBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.UserParam;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.UserService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.ParamNullUtil;
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
    }

    /**
     * 修改用户信息
     */
    @RequestMapping("/update")
    public void updateUser(HttpServletResponse response,UserParam userParam) {

        //如果id或者参数为空,提示参数异常
        if (userParam.getId()==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！")) ;
            super.safeJsonPrint(response, result);
        }
        Integer i = userService.updateUser(userParam);
        if (i>0){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功！")) ;
            super.safeJsonPrint(response, result);
            return;
        }else {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure( "修改失败！")) ;
            super.safeJsonPrint(response, result);
        }


    }
}
