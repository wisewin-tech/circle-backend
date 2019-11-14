package com.wisewin.backend.web.controller;

import com.wisewin.backend.common.constants.CircleConstants;
import com.wisewin.backend.entity.bo.CarIncidentBO;
import com.wisewin.backend.entity.bo.MatchingFriendEdBO;
import com.wisewin.backend.entity.bo.UserBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.CarIncidentService;
import com.wisewin.backend.service.MatchingFriendEdService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 事件
 */
@Controller
@RequestMapping("/CarIncident")
public class CarIncidentController extends BaseCotroller {
    @Resource
    CarIncidentService carIncidentService;
    @Resource
    MatchingFriendEdService matchingFriendEdService;

    //查看事件列表
    @RequestMapping("/getCarIncidentList")
    public void getCarIncidentList(CarIncidentBO carIncidentBO, Integer pageNo,Integer pageSize, Date beforeTime,Date afterTime,  HttpServletRequest request, HttpServletResponse response) {
        //验证参数
        if (carIncidentBO == null) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            return;
        }
        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);
        //封装条件
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("pageOffset", queryInfo.getPageOffset());
        queryMap.put("pageSize", queryInfo.getPageSize());
        queryMap.put("userId", carIncidentBO.getUserId());
        queryMap.put("phone", carIncidentBO.getPhone());
        queryMap.put("afterTime", afterTime);
        queryMap.put("beforeTime",beforeTime);
        queryMap.put("origin",carIncidentBO.getOrigin());
        queryMap.put("destination",carIncidentBO.getDestination());
        queryMap.put("incidentStatus",carIncidentBO.getIncidentStatus());
        //查询
        Integer count = carIncidentService.getCarIncidentCount(queryMap);
        List<CarIncidentBO> carIncidentBOS = new ArrayList<CarIncidentBO>();
        if (count != 0) {
            carIncidentBOS = carIncidentService.getCarIncident(queryMap);
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("carIncidentBOS", carIncidentBOS);
        resultMap.put("count", count);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));
        super.safeJsonPrint(response, json);
        return;
    }

    //根据事件查看这个人在car模式下匹配到的朋友
    @RequestMapping("/getFriendsByCarIncident")
    public void getFriendsByCarIncident(Long userId,Integer pageNo,Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
        //验证参数
        if (userId == null) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            return;
        }
        //查询
        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("pageOffset", queryInfo.getPageOffset());
        queryMap.put("pageSize", queryInfo.getPageSize());
        queryMap.put("model", CircleConstants.CAR.getValue());
        queryMap.put("userId",userId);
        Integer count = matchingFriendEdService.getFriendListCount(queryMap);
        List<MatchingFriendEdBO> matchingFriendEdBOS = new ArrayList<MatchingFriendEdBO>();
        if (count != 0) {
            matchingFriendEdBOS = matchingFriendEdService.getFriendList(queryMap);
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("matchingFriendEdBOS", matchingFriendEdBOS);
        resultMap.put("count", count);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));
        super.safeJsonPrint(response, json);
        return;
    }

}
