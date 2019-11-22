package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.CarCertificationBO;
import com.wisewin.backend.entity.bo.UserReportBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.CarCertificationService;
import com.wisewin.backend.service.UserReportService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/CarCertification")
public class CarCertificationController extends BaseCotroller {
    @Resource
    CarCertificationService carCertificationService;

    @RequestMapping("/getCarIncidentList")
    public void getCarIncidentList(String status, Integer pageNo, Integer pageSize, Date beforeTime, Date afterTime, HttpServletRequest request, HttpServletResponse response) {
        //验证参数
        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);
        //查询
        Integer count = carCertificationService.getCarCertificationListCount(status,beforeTime,afterTime);
        List<CarCertificationBO> carCertificationBOS = new ArrayList<CarCertificationBO>();
        if (count != 0) {
            carCertificationBOS = carCertificationService.getCarCertificationList(status, queryInfo.getPageOffset(), queryInfo.getPageSize(),beforeTime,afterTime);
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("carCertificationBOS", carCertificationBOS);
        resultMap.put("count", count);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));
        super.safeJsonPrint(response, json);
        return;
    }

    @RequestMapping("/updUserReport")
    public void updUserReport(CarCertificationBO carCertificationBO, HttpServletRequest request, HttpServletResponse response) {
        //验证参数
        if (carCertificationBO==null) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            return;
        }
        carCertificationService.updCarCertification(carCertificationBO);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("0000000"));
        super.safeJsonPrint(response, json);
        return;
    }

}
