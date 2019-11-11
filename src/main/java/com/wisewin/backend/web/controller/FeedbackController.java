package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.FeedBackBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.FeedbackService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/feedback")
public class FeedbackController extends BaseCotroller {
    @Resource
    private FeedbackService feedbackService;

    /**
     * 通过状态查询意见反馈
     *
     * @param status 状态 yes已读，no未读
     */
    @RequestMapping("/getFeedbackListCount")
    public void getFeedbackListCount(Integer pageNo, Integer pageSize, String status, HttpServletRequest request, HttpServletResponse response) {
        //验证参数
        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);
        Integer count = feedbackService.getFeedbackListCount(status);
        List<FeedBackBO> feedBackBOS = new ArrayList<FeedBackBO>();
        if (count > 0) {
            feedBackBOS = feedbackService.getFeedbackList(status, queryInfo.getPageOffset(), queryInfo.getPageSize());
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("feedBackResultBOList", feedBackBOS);
        resultMap.put("count", count);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));
        super.safeJsonPrint(response, result);
    }

    /**
     * 修改意见反馈信息
     */
    @RequestMapping("/updFeedback")
    public void updFeedback(FeedBackBO feedBackBO, HttpServletRequest request, HttpServletResponse response) {
        //参数验证
        if (feedBackBO == null) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            return;
        }
        AdminBO adminBO=super.getLoginUser(request);
        feedBackBO.setAdminId(adminBO.getId());
        feedbackService.updFeedback(feedBackBO);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("0000000"));
        super.safeJsonPrint(response, result);


    }
}
