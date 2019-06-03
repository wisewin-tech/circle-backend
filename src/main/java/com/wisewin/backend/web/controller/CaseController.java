package com.wisewin.backend.web.controller;

import com.wisewin.backend.dao.TestDAO;
import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.CaseBO;
import com.wisewin.backend.entity.bo.InterestBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.CaseService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 认证案例
 */
@Controller
@RequestMapping("/Case")
public class CaseController extends BaseCotroller {

    static final Logger log = LoggerFactory.getLogger(CaseController.class);

    @Resource
    private CaseService caseService;

    @RequestMapping("/addCase")
    public void addCase(HttpServletResponse response, HttpServletRequest request, CaseBO caseBO) {
        //参数异常
        if(caseBO==null||caseBO.getImgUrl()==null){
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
        caseBO.setCreateUserId(loginAdmin.getId());

        //添加
        if(caseService.addCase(caseBO)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功"));
            super.safeJsonPrint(response, json);
        }else{
            String result= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002"));
            super.safeHtmlPrint(response,result);
        }

    }

    @RequestMapping("/updCase")
    public void updCase(HttpServletResponse response, HttpServletRequest request,CaseBO caseBO) {
        //参数异常
        if (caseBO == null||caseBO.getId()==null) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
        }
        //获取管理员
        AdminBO loginAdmin = super.getLoginUser(request);
        if (loginAdmin == null) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
            super.safeHtmlPrint(response, result);
            return;
        }
        caseBO.setUpdateUserId(loginAdmin.getId());
        //添加
        if(caseService.updCase(caseBO)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功"));
            super.safeJsonPrint(response, json);
        }else{
            String result= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002"));
            super.safeHtmlPrint(response,result);
        }
    }

        @RequestMapping("/getCase")
    public void getCase(HttpServletResponse response, HttpServletRequest request) {
        //查询
        List<CaseBO> caseBOS=caseService.getCaseImg();
        String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(caseBOS));
        super.safeJsonPrint(response,json);
    }

}
