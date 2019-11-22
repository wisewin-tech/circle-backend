package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.StatisticalService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping("/Statistical")
public class StatisticalController extends BaseCotroller {

    @Resource
    StatisticalService statisticalService;

    @RequestMapping("/getStatistical")
    public void getStatistical(Date date, HttpServletResponse response){
        if (date == null) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            return;
        }
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(statisticalService.getStatisticalMap(date)));
        super.safeJsonPrint(response, result);
        return;
    }
}
