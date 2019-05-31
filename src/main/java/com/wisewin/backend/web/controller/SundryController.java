package com.wisewin.backend.web.controller;


import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.SundryBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.SundryService;
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
@RequestMapping("/sundry")
public class SundryController extends BaseCotroller {
    @Resource
    private SundryService sundryService ;

    /**
     * 查询关于我们
     * @param response
     */
    @RequestMapping("/selectAboutUs")
    public void select(HttpServletResponse response) {
        //通过查询信息,返回aboutUs对象
        SundryBO aboutUs=sundryService.selectContent();

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
    @RequestMapping("/updateAboutUs")
    public void update(HttpServletResponse response, HttpServletRequest request,SundryBO aboutUsBO) {
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
        if(sundryService.updateAbouUs(aboutUsBO)){

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
