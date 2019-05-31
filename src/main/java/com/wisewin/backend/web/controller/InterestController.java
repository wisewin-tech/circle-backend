package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.InterestBO;
import com.wisewin.backend.entity.bo.InterestSubclassBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.InterestService;
import com.wisewin.backend.service.InterestSubclassService;
import com.wisewin.backend.util.JsonUtils;
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
@RequestMapping("/Interest")
public class InterestController extends BaseCotroller {

    static final Logger log = LoggerFactory.getLogger(InterestController.class);

    @Resource
    private InterestService interestService;

    /**
     * 添加一级兴趣
     * */
    @RequestMapping("/addInterest")
    public void addInterestSubclass(InterestBO interestBO, HttpServletRequest request, HttpServletResponse response) {
        //参数异常
        if(interestBO==null||interestBO.getInterestTypeId()==null||interestBO.getInterestName()==null||interestBO.getGrade()==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
        }

        //获取管理员
        AdminBO loginAdmin = super.getLoginUser(request);
        if(loginAdmin==null){
            String result= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
            super.safeHtmlPrint(response,result);
            return;
        }
        Integer adminId = loginAdmin.getId();
        interestBO.setCreateUserId(adminId);
        //添加
        if(interestService.addInterest(interestBO)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功"));
            super.safeJsonPrint(response, json);
        }else{
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002"));
            super.safeHtmlPrint(response,languagejson);
        }

    }

    /**
     * 删除一级兴趣
     * */
    @RequestMapping("/delInterest")
    public void delInterest(Integer id,HttpServletRequest request, HttpServletResponse response) {
        //参数异常
        if(id==null||id.equals("")||id==0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
        }

        //删除
        if(interestService.delInterest(id)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除成功"));
            super.safeJsonPrint(response, json);
        }else{
            String result= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002"));
            super.safeHtmlPrint(response,result);
        }

    }


    /**
     * 查询一级兴趣
     * */
    @RequestMapping("/getInterest")
    public void getInterest(Integer typeId, HttpServletRequest request, HttpServletResponse response) {
        //参数异常
        if(typeId==null||typeId.equals("")||typeId==0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
        }

        //查询
        List<InterestBO> interestBOS=interestService.getInterestsByTypeId(typeId);
        String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(interestBOS));
        super.safeJsonPrint(response,json);
    }



}
