package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.InterestBO;
import com.wisewin.backend.entity.bo.InterestSubclassBO;
import com.wisewin.backend.entity.bo.InterestTypeBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.InterestSubclassService;
import com.wisewin.backend.service.InterestTypeService;
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
 * 兴趣分类管理
 */
@Controller
@RequestMapping("/InterestType")
public class InterestTypeController extends BaseCotroller {

    static final Logger log = LoggerFactory.getLogger(InterestTypeController.class);

    @Resource
    private InterestTypeService interestTypeService;

    /**
     * 添加兴趣分类
     * */
    @RequestMapping("/addInterestType")
    public void addInterestType(InterestTypeBO interestTypeBO, HttpServletRequest request, HttpServletResponse response) {
        //参数异常
        if(interestTypeBO==null||interestTypeBO.getTypeName()==null){
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
        interestTypeBO.setCreateUserId(adminId);
        //添加
        if(interestTypeService.addInterestType(interestTypeBO)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功"));
            super.safeJsonPrint(response, json);
        }else{
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002"));
            super.safeHtmlPrint(response,languagejson);
        }
    }

    /**
     * 删除兴趣分类
     * */
    @RequestMapping("/delInterestType")
    public void delInterestType(Integer id, HttpServletRequest request, HttpServletResponse response) {
        //参数异常
        if(id==null||id.equals("")||id==0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
        }

        //删除
        if(interestTypeService.delInterestType(id)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除成功"));
            super.safeJsonPrint(response, json);
        }else{
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002"));
            super.safeHtmlPrint(response,languagejson);
        }

    }


    /**
     * 查询兴趣分类
     * */
    @RequestMapping("/getInterestType")
    public void getInterestType(HttpServletRequest request, HttpServletResponse response) {
        //查询
        List<InterestTypeBO> interestSubclassBOS=interestTypeService.getInterestsType();
        String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(interestSubclassBOS));
        super.safeJsonPrint(response,json);
    }



}
