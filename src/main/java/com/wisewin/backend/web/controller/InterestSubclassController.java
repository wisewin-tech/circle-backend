package com.wisewin.backend.web.controller;

import com.wisewin.backend.dao.InterestSubclassDAO;
import com.wisewin.backend.dao.TestDAO;
import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.InterestSubclassBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
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
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 二级兴趣管理
 */
@Controller
@RequestMapping("/InterestSubclass")
public class InterestSubclassController extends BaseCotroller {

    static final Logger log = LoggerFactory.getLogger(InterestSubclassController.class);

    @Resource
    private InterestSubclassService interestSubclassService;

    /**
     * 添加二级兴趣
     * */
    @RequestMapping("/addInterestSubclass")
    public void addInterestSubclass(InterestSubclassBO interestSubclassBO, HttpServletRequest request, HttpServletResponse response) {
        //参数异常
        if(interestSubclassBO==null||interestSubclassBO.getFatherId()==null||interestSubclassBO.getName()==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
        }

        //获取管理员
        AdminBO loginAdmin = super.getLoginUser(request);
        if(loginAdmin==null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        Integer adminId = loginAdmin.getId();
        interestSubclassBO.setCreateUserId(adminId);
        //添加
        if(interestSubclassService.addInterestSubclass(interestSubclassBO)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功"));
            super.safeJsonPrint(response, json);
        }else{
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002"));
            super.safeHtmlPrint(response,languagejson);
        }


    }

    /**
     * 修改二级兴趣
     * */
    @RequestMapping("/updInterestSubclass")
    public void updInterestSubclass(InterestSubclassBO interestSubclassBO, HttpServletRequest request, HttpServletResponse response) {
        //参数异常
        if(interestSubclassBO==null||interestSubclassBO.getName()==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
        }

        //获取管理员
        AdminBO loginAdmin = super.getLoginUser(request);
        if(loginAdmin==null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        Integer adminId = loginAdmin.getId();
        interestSubclassBO.setUpdateUserId(adminId);
        //添加
        if(interestSubclassService.updInterestSubclass(interestSubclassBO)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功"));
            super.safeJsonPrint(response, json);
        }else{
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002"));
            super.safeHtmlPrint(response,languagejson);
        }


    }

    /**
     * 删除二级兴趣
     * */
    @RequestMapping("/delInterestSubclass")
    public void delInterestSubclass(Integer id, HttpServletRequest request, HttpServletResponse response) {
        //参数异常
        if(id==null||id.equals("")||id==0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
        }

        //删除
        if(interestSubclassService.delInterestSubclass(id)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除成功"));
            super.safeJsonPrint(response, json);
        }else{
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002"));
            super.safeHtmlPrint(response,languagejson);
        }

    }


    /**
     * 查询二级兴趣
     * */
    @RequestMapping("/getInterestSubclass")
    public void getInterestSubclass(Integer interestId, HttpServletRequest request, HttpServletResponse response) {
        //参数异常
        if(interestId==null||interestId.equals("")||interestId==0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
        }

        //查询
        List<InterestSubclassBO> interestSubclassBOS=interestSubclassService.getInterestsSubclassByInterestId(interestId);
        String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(interestSubclassBOS));
        super.safeJsonPrint(response,json);
    }



}
