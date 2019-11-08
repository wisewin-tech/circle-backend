package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.InterestBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.InterestService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 二级兴趣管理
 */
@Controller
@RequestMapping("/interest")
public class InterestController extends BaseCotroller {

    static final Logger log = LoggerFactory.getLogger(InterestController.class);

    @Resource
    private InterestService interestService;


    /**
     * 查询兴系统兴趣
     * @param request
     * @param response
     * @param typeId
     */
    @RequestMapping("/queryInterestByTypeId")
    public void queryInterestByTypeId(HttpServletRequest request, HttpServletResponse response ,Integer typeId ) {
        List<InterestBO> interestBOS = interestService.queryInterestByTypeId(typeId);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(interestBOS));
        super.safeJsonPrint(response, json);
        log.info("result:{}",json);
    }


    @RequestMapping("/updateInterest")
    public void updateInterest(HttpServletRequest request, HttpServletResponse response ,InterestBO interestBO) {
        if(StringUtils.isEmpty(interestBO.getInterestName()) || interestBO.getTypeId()==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            log.info("result:{}",json);
            return;
        }
        interestService.updateInterest(interestBO);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        super.safeJsonPrint(response, json);
        log.info("result:{}",json);
    }


    /**
     * 添加兴趣
     * @param interestBO
     * @return
     */
    @RequestMapping("/addInterest")
    public void addInterest(HttpServletRequest request, HttpServletResponse response ,InterestBO interestBO) {
        if(StringUtils.isEmpty(interestBO.getInterestName()) || interestBO.getTypeId()==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            log.info("result:{}",json);
            return;
        }
        interestService.addInterest(interestBO);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        super.safeJsonPrint(response, json);
        log.info("result:{}",json);
    }

    /**
     * 删除兴趣
     * @param id  兴趣id
     * @return
     */
    @RequestMapping("/delInterest")
    public void delInterest(HttpServletRequest request, HttpServletResponse response,Integer id) {
        //参数异常
        if(id==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            log.info("result:{}",json);
            return;
        }
        interestService.delInterest(id);
        String result= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        super.safeHtmlPrint(response,result);
        log.info("result:{}",result);

    }


    /**
     * 查询兴趣分类
     * @param request
     * @param response
     */
    @RequestMapping("/queryInterestType")
    public void queryInterestType(HttpServletRequest request, HttpServletResponse response) {
        List<InterestBO> interestBOS = interestService.queryInterestType();
        String result= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(interestBOS));
        super.safeHtmlPrint(response,result);
        log.info("result:{}",result);

    }


}
