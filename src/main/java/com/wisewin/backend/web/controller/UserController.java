package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.UserParam;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.UserService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.ParamNullUtil;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseCotroller{
    @Resource
    private UserService userService;

    /**
     * 查询所有用户信息
     * pageNo 页数
     * pageSize 每页条数
     * @param response
     */
    @RequestMapping("/selectAll")
    public void selectAll(Integer pageNo, Integer pageSize,HttpServletResponse response,UserParam userParam) {

        //封装limit条件,pageNo改为页数
        QueryInfo queryInfo = getQueryInfo(pageNo,pageSize);
        //创建一个用于封装sql条件的map集合
        Map<String, Object> condition = new HashMap<String, Object>();
        if(queryInfo != null){
            //把pageOffset 页数,pageSize每页的条数放入map集合中
            condition.put("pageOffset", queryInfo.getPageOffset());
            condition.put("pageSize", queryInfo.getPageSize());
        }
        //把带有条件的查询结果集放入map中
        Map<String,Object>  resultMap= userService.selectAll(condition,userParam);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));
        super.safeJsonPrint(response, json);
    }

    /**
     * 修改用户信息
     */
    @RequestMapping("/update")
    public void updateUser(HttpServletResponse response,Integer id,UserParam userParam) {
       System.out.println(ParamNullUtil.checkObjAllFieldsIsNull(userParam));
        //如果id或者参数为空,提示参数异常
        if (id==null||ParamNullUtil.checkObjAllFieldsIsNull(userParam) ){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！")) ;
            super.safeJsonPrint(response, result);
        }
            //如果通过id可以查询到user对象
       if ( userService.updateUser(id,userParam)) {
           String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改信息成功"));
           super.safeJsonPrint(response, json);
       }else{//否则,提示该用户不存在
           String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001","该用户不存在"));
           super.safeJsonPrint(response, json);
       }
    }

    /**
     * 删除用户信息
     * @param response
     * @param id
     */
    @RequestMapping("/delete")
    public void deleteUser(HttpServletResponse response,Integer id) {
        if (id==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！")) ;
            super.safeJsonPrint(response, result);
        }
            //如果查询到用户,删除
        if (userService.deleteUser(id)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除信息成功"));
            super.safeJsonPrint(response, json);
        }else{ //否则提示用户不存在
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001","该用户不存在"));
            super.safeJsonPrint(response, json);
        }

    }
}
