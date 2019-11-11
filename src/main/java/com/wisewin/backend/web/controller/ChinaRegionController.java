package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.ChinaRegionBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.ChinaRegionService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/ChinaRegionController")
public class ChinaRegionController extends BaseCotroller {
    @Resource
    ChinaRegionService chinaRegionService;

    //查询列表
    @RequestMapping("/getChinaRegionBOList")
    public void getChinaRegionBOList(HttpServletResponse response, HttpServletRequest request) throws InterruptedException {
        List<ChinaRegionBO> list= chinaRegionService.getChinaRegionBOList();
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(list));
        super.safeJsonPrint(response, json);
    }

    //修改名称
    @RequestMapping("/updChinaRegion")
    public void updChinaRegion(ChinaRegionBO chinaRegionBO,HttpServletResponse response, HttpServletRequest request) throws InterruptedException {
        chinaRegionService.updChinaRegion(chinaRegionBO);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("0000000"));
        super.safeJsonPrint(response, json);
    }

    //删除
    @RequestMapping("/delChinaRegion")
    public void delChinaRegion(Long id,HttpServletResponse response, HttpServletRequest request) throws InterruptedException {
        chinaRegionService.delChinaRegion(id);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("0000000"));
        super.safeJsonPrint(response, json);
    }

    //增加地区
    @RequestMapping("/addChinaRegionBO")
    public void addChinaRegionBO(ChinaRegionBO chinaRegionBO,HttpServletResponse response, HttpServletRequest request) throws InterruptedException {
        chinaRegionService.addChinaRegionBO(chinaRegionBO);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("0000000"));
        super.safeJsonPrint(response, json);
    }


}
