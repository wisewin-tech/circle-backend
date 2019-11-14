package com.wisewin.backend.web.controller;

import com.wisewin.backend.common.constants.CircleConstants;
import com.wisewin.backend.entity.bo.MatchingFriendEdBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.MatchingFriendEdService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 好友
 */
@Controller
@RequestMapping("/MatchingFriendEd")
public class MatchingFriendEdController extends BaseCotroller {

    @Resource
    MatchingFriendEdService matchingFriendEdService;

    @RequestMapping("/getFriendsList")
    public void getFriendsList(Long userId,String model,String friendsStatus, Integer pageNo, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
        //查询
        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("pageOffset", queryInfo.getPageOffset());
        queryMap.put("pageSize", queryInfo.getPageSize());
        queryMap.put("model", model);
        queryMap.put("userId",userId);
        queryMap.put("friendsStatus",friendsStatus);

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
