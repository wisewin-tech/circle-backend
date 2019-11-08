package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.UserCertificationDemoBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.UserCertificationDemoService;
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
 * 认证demo管理
 */
@Controller
@RequestMapping("/UserCertificationDemo")
public class UserCertificationDemoController extends BaseCotroller {

    static final Logger log = LoggerFactory.getLogger(UserCertificationDemoController.class);

    @Resource
    private UserCertificationDemoService userCertificationDemoService;

    //获取认证案例
    @RequestMapping("/getCertificationDemoList")
    public void getCertificationDemoList(HttpServletResponse response, HttpServletRequest request) {
        List<UserCertificationDemoBO> result = userCertificationDemoService.getCertificationDemoList();
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(result));
        super.safeJsonPrint(response, json);
    }

    //删除认证案例
    @RequestMapping("delCertificationDemo")
    public void delCertificationDemo(HttpServletResponse response, HttpServletRequest request, Long demoId) {
        if (demoId == null) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
        }
        userCertificationDemoService.delCertificationDemo(demoId);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("0000000"));
        super.safeJsonPrint(response, json);
    }

    //添加认证案例
    @RequestMapping("addCertificationDemo")
    public void addCertificationDemo(HttpServletResponse response, HttpServletRequest request, UserCertificationDemoBO userCertificationDemoBO) {
        if (userCertificationDemoBO == null) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
        }
        userCertificationDemoService.addCertificationDemo(userCertificationDemoBO);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("0000000"));
        super.safeJsonPrint(response, json);
    }

}
