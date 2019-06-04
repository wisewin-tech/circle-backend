package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.TheGarageImgBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.TheGarageImgService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/theGarageImg")
public class TheGarageImgController extends BaseCotroller{
    @Resource
    private TheGarageImgService theGarageImgService;

    /**
     * 查询车库图片信息
     * @return
     */
    @RequestMapping("/queryTheGarageImg")
    public void queryTheGarageImg(HttpServletRequest request, HttpServletResponse response){
        List<TheGarageImgBO> theGarageImgBOS = theGarageImgService.queryTheGarageImg();
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(theGarageImgBOS));
        super.safeJsonPrint(response, json);

    }

    /**
     * 批量删除车库图片信息
     * @param idArrJSON
     * @param request
     * @param response
     */
    @RequestMapping("/delTheGarageImg")
    public void delTheGarageImg(String idArrJSON,HttpServletRequest request,HttpServletResponse response){
            //参数验证
        if (StringUtils.isEmpty(idArrJSON)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }
        String[] split = idArrJSON.split(",");
        Integer [] ints= new Integer [split.length];

        for (int i = 0; i < split.length; i++) {
            ints[i]=Integer.parseInt(split[i]);
        }

        if (ints.length==0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }
        Integer i = theGarageImgService.delTheGarageImg(ints);
        if (i>0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除车库图片成功！共删除"+i+"条记录"));
            super.safeJsonPrint(response, json);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("删除车库图片失败！"));
        super.safeJsonPrint(response, json);
    }
}
