package com.wisewin.backend.web.controller;

import com.wisewin.backend.dao.TestDAO;
import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.UserAuthImgBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.UserAuthImgService;
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
 * 用户认证
 */
@Controller
@RequestMapping("/UserAuth")
public class UserAuthController extends BaseCotroller {

    static final Logger log = LoggerFactory.getLogger(UserAuthController.class);

    @Resource
    private UserAuthImgService userAuthImgService;

    @RequestMapping("/getUserAuth")
    public void getUserAuth(HttpServletResponse response, HttpServletRequest request,String status,Integer userId) {
        List<UserAuthImgBO> result=userAuthImgService.getUserAuth(userId,status);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(result));
        super.safeJsonPrint(response, json);
    }

    @RequestMapping("updUserAuth")
    public void updUserAuth(HttpServletResponse response,HttpServletRequest request,UserAuthImgBO userAuthImgBO){
        if(userAuthImgBO==null||userAuthImgBO.getId()==null||userAuthImgBO.getStatus()==null||userAuthImgBO.getNotice()==null){
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
        userAuthImgBO.setAdminId(loginAdmin.getId());
        if(userAuthImgService.updUserAuth(userAuthImgBO)>0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功"));
            super.safeJsonPrint(response, json);
        }else{
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002"));
            super.safeHtmlPrint(response,languagejson);
        }
    }
}
