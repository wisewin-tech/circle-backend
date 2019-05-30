package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.NewbitHelpContentBO;
import com.wisewin.backend.entity.param.NewditHelpParam;
import com.wisewin.backend.entity.bo.NewbitHelpBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.NewbitHelpContentService;
import com.wisewin.backend.service.NewbitHelpService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 新手帮助控制器层
 */
@Controller
@RequestMapping("/newbitHelp")
public class NewbitHelpController extends BaseCotroller {
    @Resource
    private NewbitHelpService newbitHelpService;
    @Resource
    private NewbitHelpContentService newbitHelpContentService;

    /**
     * 查询新手帮助标题
     * @param request
     * @param response
     */
    @RequestMapping("/selectNewbitHelp")
    public void newbitHelpList(HttpServletRequest request, HttpServletResponse response){
        List<NewbitHelpBO> newbitHelpBOList = newbitHelpService.selectNewbitHelp();
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(newbitHelpBOList)) ;
        super.safeJsonPrint(response, result);

    }

    /**
     * 新增新手帮助标题
     * @param request
     * @param response
     * @param param
     */
    @RequestMapping("/insertNewbitHelp")
    public void insertNewbitHelp(HttpServletRequest request, HttpServletResponse response,NewditHelpParam param){
        //参数验证
        if (param==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001","参数异常"));
            super.safeJsonPrint(response, result);
            return;
        }
        Integer i = newbitHelpService.insertNewbitHelp(param);
        if(i>0) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("新手帮助标题添加完成！"));
            super.safeJsonPrint(response, result);
        }
    }

    /**
     * 删除新手帮助标题
     * @param
     * @param request
     * @param response
     */
    @RequestMapping("/delNewditHelp")
    public void delNewditHelp(Integer id, HttpServletRequest request, HttpServletResponse response){
        //参数验证
        if (StringUtils.isEmpty(String.valueOf(id))){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001","参数异常"));
            super.safeJsonPrint(response, result);
            return;
        }

        //执行删除操作
        boolean i = newbitHelpService.deleteNewbitHelp(id);
        if (i) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("新手帮助标题删除成功！"));
            super.safeJsonPrint(response, result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000007","新手帮助标题删除失败！"));
            super.safeJsonPrint(response, result);
        }
    }

    /**
     * 修改新手帮助标题
     * @param request
     * @param response
     * @param param
     */
    @RequestMapping("/editNewditHelp")
    public void editNewditHelp(HttpServletRequest request,HttpServletResponse response,NewditHelpParam param){
    //参数验证
        if (param==null||StringUtils.isEmpty(param.getMasterTitle())){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001","参数异常"));
            super.safeJsonPrint(response, result);
            return;
        }
        //执行修改
         newbitHelpService.editNewditHelp(param);
        if (newbitHelpService.editNewditHelp(param)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改新手帮助标题成功！"));
            super.safeJsonPrint(response, result);
            return;
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001","修改新手帮助标题失败！"));
            super.safeJsonPrint(response, result);
            return;
        }
    }

    /**
     * 添加新手帮助内容
     * @param newbitHelpContentBO
     * @param request
     * @param response
     */
    @RequestMapping("addNewbitHelpContent")
    public void addNewbitHelpContent(NewbitHelpContentBO newbitHelpContentBO, HttpServletRequest request, HttpServletResponse response){
        //参数验证
        if (StringUtils.isEmpty(newbitHelpContentBO.getContent())||newbitHelpContentBO.getHelpId()==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            return;
        }
        Integer i = newbitHelpContentService.addNewbitHelpContent(newbitHelpContentBO);
        if (i>0){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("新手帮助内容添加完成！"));
            super.safeJsonPrint(response, result);
        }

    }

    /**
     * 修改新手帮助内容
     * @param newbitHelpContentBO
     * @param request
     * @param response
     */
    @RequestMapping("updNewbitHelpContent")
    public void updNewbitHelpContent(NewbitHelpContentBO newbitHelpContentBO,HttpServletRequest request,HttpServletResponse response){
        //参数验证
        if (newbitHelpContentBO.getId()==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            return;
        }
        //执行修改
        boolean i = newbitHelpContentService.updNewbitHelpContentBO(newbitHelpContentBO);
        if (i){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改新手帮助内容成功！"));
            super.safeJsonPrint(response, result);
            return;
        }else {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("","修改新手帮助内容失败！"));
            super.safeJsonPrint(response, result);
        }

    }

    /**
     * 删除新手帮助内容
     * @param id
     * @param request
     * @param response
     */
    @RequestMapping("delNewbitHelpContent")
    public void delNewbitHelpContent(Integer id,HttpServletRequest request,HttpServletResponse response){
        if (id==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            return;
        }
         boolean i = newbitHelpContentService.delNewbitHelpContentBO(id);
        if (i){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除新手帮助内容成功"));
            super.safeJsonPrint(response, result);
            return;
        }else {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("删除新手帮助内容失败"));
            super.safeJsonPrint(response, result);
        }
    }

    /**
     * 查询新手帮助有信息
     * @param request
     * @param response
     */
    @RequestMapping("getNewbitHelpContent")
    public void getNewbitHelpContent(HttpServletRequest request,HttpServletResponse response){

        List<NewbitHelpContentBO> newbitHelpContentBOS = newbitHelpContentService.getNewbitHelpContentBO();
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(newbitHelpContentBOS));
        super.safeJsonPrint(response, result);

    }
}
