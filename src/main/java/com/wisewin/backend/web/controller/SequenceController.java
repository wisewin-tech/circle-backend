package com.wisewin.backend.web.controller;


import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.StsUtil;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/sequence")
public class SequenceController extends BaseCotroller {





    /**
      *  获取OSS临时凭证
      */
    @RequestMapping(value = "/getStsOss" , method = RequestMethod.POST)
    public void getOssSts(HttpServletRequest request,HttpServletResponse response) {
        AdminBO loginUser = super.getLoginUser(request);
        if(loginUser==null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        Map<String, String> stsMessage = StsUtil.getStsOss(loginUser.getId()+"AdminOss");
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(stsMessage));
        super.safeJsonPrint(response, json);
        return;
    }



}
