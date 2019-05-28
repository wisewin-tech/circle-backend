package com.wisewin.backend.web.controller;


import com.wisewin.backend.entity.bo.AboutUsBO;
import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.AboutUsService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 关于我们
 */
@Controller
@RequestMapping("/aboutUs")
public class AboutUsController extends BaseCotroller {
    @Resource
    private AboutUsService aboutUsService ;

    //查询
    @RequestMapping("/select")
    public void select(HttpServletResponse response) {
        //通过查询信息,返回aboutUs对象
        AboutUsBO aboutUs=aboutUsService.selectContent();

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(aboutUs));
        super.safeJsonPrint(response, json);
    }

    /**
     * 修改关于我们
     * 更新信息,没有就添加,有,就修改,多了就报错
     * @param response
     * @param request
     * @param aboutUsBO
     */
    @RequestMapping("/update")
    public void update(HttpServletResponse response, HttpServletRequest request,AboutUsBO aboutUsBO) {
        //参数非空验证
        if(aboutUsBO==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！")) ;
            super.safeJsonPrint(response, result);
            return;
        }
        //获取登录对象
       // AdminBO adminBO = super.getLoginUser(request);
        //Integer createUserId = adminBO.getId();
            //对表里数据判断,符合条件的添加和修改,不符合返回false
        if(aboutUsService.updateAbouUs(aboutUsBO)){

            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("信息修改成功"));
            super.safeJsonPrint(response, json);
            return;
        }else{
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001","数据异常"));
           super.safeJsonPrint(response, json);
            return;

        }

    }


}
