package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.UserReportBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.UserReportService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 举报
 */
@Controller
@RequestMapping("/userReport")
public class UserReportController extends BaseCotroller {
    @Resource
    UserReportService userReportService;

    //查询举报列表
    @RequestMapping("/getUserReportList")
    public void getCarIncidentList(String status, Integer pageNo, Integer pageSize, Date beforeTime, Date afterTime, HttpServletRequest request, HttpServletResponse response) {
        //验证参数
        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);
        //查询
        Integer count = userReportService.getUserReportListCount(status);
        List<UserReportBO> userReportBOS = new ArrayList<UserReportBO>();
        if (count != 0) {
            userReportBOS = userReportService.getUserReportList(status, queryInfo.getPageOffset(), queryInfo.getPageSize());
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("userReportBOS", userReportBOS);
        resultMap.put("count", count);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));
        super.safeJsonPrint(response, json);
        return;
    }

    //修改举报状态
    @RequestMapping("/updUserReport")
    public void updUserReport(UserReportBO userReportBO, HttpServletRequest request, HttpServletResponse response) {
        //验证参数
        if (userReportBO==null) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            return;
        }
        //查询
        userReportService.updUserReport(userReportBO);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("0000000"));
        super.safeJsonPrint(response, json);
        return;
    }

}
