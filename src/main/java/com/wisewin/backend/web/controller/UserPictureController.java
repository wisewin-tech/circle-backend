package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.KeyValueBO;
import com.wisewin.backend.entity.bo.UserPictureBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.KeyValueService;
import com.wisewin.backend.service.UserPictureService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userPicture")
public class UserPictureController extends BaseCotroller {
    @Resource
    private UserPictureService userPictureService;

    @Resource
    private KeyValueService keyValueService;
    /**
     * 获取审核背景图列表
     * @param sort 排序码
     * @param model 模式
     */
    @RequestMapping("/getPictureByStatus")
    public void getPictureByStatus(String status,Long sort, String model, Integer pageNo, Integer pageSize,HttpServletRequest request, HttpServletResponse response) {
        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);
        Integer count = userPictureService.getPictureByStatusCount(status,sort,model);
        List<UserPictureBO> pictureBOS = new ArrayList<UserPictureBO>();
        if (count >= 0) {
            pictureBOS = userPictureService.getPictureByStatus(status,sort,model, queryInfo.getPageOffset(), queryInfo.getPageSize());
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", count);
        map.put("pictureBOS", pictureBOS);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(map));
        super.safeJsonPrint(response, json);
    }

    /**
     * 修改背景图片
     */
    @RequestMapping("/updPictureById")
    public void updPictureById(UserPictureBO userPictureBO, HttpServletRequest request, HttpServletResponse response) {
        //验证参数
        if (userPictureBO == null || userPictureBO.getId() == null) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            return;
        }
        KeyValueBO keyValue =keyValueService.getValueByKey("illegalPic");
        if(keyValue==null||keyValue.getValues()==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000005"));
            super.safeJsonPrint(response, result);
            return;
        }
        userPictureBO.setPictureUrl(keyValue.getValues());
        userPictureService.updPictureById(userPictureBO);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("0000000"));
        super.safeJsonPrint(response, json);
    }

}
