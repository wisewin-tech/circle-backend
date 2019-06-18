package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.BackgroundBO;
import com.wisewin.backend.entity.bo.UserBackgroundBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.dto.UserBackBTO;
import com.wisewin.backend.entity.dto.WallDTO;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.UserBackgroundService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.apache.struts.config.BaseConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Bin Wang
 * @date: Created in 15:12 2019/6/4
 */
@Controller
@RequestMapping("/userBackground")
public class UserBackgroundController extends BaseCotroller {

    @Resource
    private UserBackgroundService userBackgroundService;

    /**
     * 查询
     * @param request
     * @param response
     * @param id
     * @param userId
     * @param status
     */
    @RequestMapping("/selBackground")
    public void selBackground(HttpServletRequest request, HttpServletResponse response, Integer id, Integer userId, String status, Integer pattern, Integer pageNo ,Integer  pageSize) {
        AdminBO loginUser=super.getLoginUser(request);
        if(loginUser == null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        Map<String, Object> map = new HashMap();
       //封装limit条件,pageNo改为页数
        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);

            //把pageOffset 页数,pageSize每页的条数放入map集合中
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());

        map.put("id", id);
        map.put("userId", userId);
        map.put("status", status);
        map.put("pattern", pattern);

        Map<String, Object> count  = new HashMap();
        count.put("id", id);
        count.put("userId", userId);
        count.put("status", status);
        count.put("pattern", pattern);
        UserBackBTO wall = userBackgroundService.queryUserBack(map,count);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(wall));
        super.safeJsonPrint(response, json);
    }

    /**
     * 修改审核状态
     * @param request
     * @param response
     * @param id
     * @param state
     */
    @RequestMapping("/updateBackgroundStatus")
    public void updateBackgroundStatus(HttpServletRequest request, HttpServletResponse response, Integer id, String state) {
        AdminBO loginUser=super.getLoginUser(request);
        if(loginUser == null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        if(id == null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        if(StringUtils.isEmpty(state)){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        userBackgroundService.updateUserBackStatus(id,state);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功"));
        super.safeJsonPrint(response, json);
    }

    /**
     * 删除操作
     * @param request
     * @param response
     * @param id
     */
    @RequestMapping("/dedelteUserBack")
    public void delteUserBack(HttpServletRequest request, HttpServletResponse response, Integer id){
        AdminBO loginUser=super.getLoginUser(request);
        if(loginUser == null) {
            String languagejson = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
            super.safeHtmlPrint(response, languagejson);
            return;
        }
        if(id == null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        userBackgroundService.delteUserBack(id);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除成功"));
        super.safeJsonPrint(response, json);
    }
}
