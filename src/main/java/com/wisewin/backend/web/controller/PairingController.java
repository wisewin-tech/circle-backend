package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.PairingBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.PairingParam;
import com.wisewin.backend.service.PairingService;
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
 * 配对
 */
@Controller
@RequestMapping("/pairing")
public class PairingController extends BaseCotroller{


    @Resource
    private PairingService pairingService;


    /**
     * 添加
     * @param
     * @return
     */
    @RequestMapping("/addPairing")
    public void addPairing(HttpServletRequest request, HttpServletResponse response, PairingParam param){

        AdminBO loginUser=super.getLoginUser(request);
        Integer id = loginUser.getId();
        if (id==null ||  StringUtils.isObjEmpty(param.getKey())||StringUtils.isObjEmpty(param.getValue())){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            return ;
        }

        //查询数据库是否有相同的数据
        Integer findPairingjoin=pairingService.findPairing(param.getKey());
        if (findPairingjoin>0){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            super.safeJsonPrint(response, result);
            return ;
        }

        //添加
        boolean addPairingjoin=pairingService.addPairing(id,id,param);
        if (addPairingjoin){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功"));
            super.safeJsonPrint(response, result);
            return ;
        }

    }

    /**
     * 根据条件显示信息
     */
    @RequestMapping("/queryPairing")
    public void queryPairing(HttpServletRequest request, HttpServletResponse response, String key, PairingParam param){

        //根据条件查询
        List<PairingBO> list=pairingService.queryPairing(key);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(list));
        super.safeJsonPrint(response, json);
        return;
    }

    /**
     * 删除
     */
    @RequestMapping("/deletePairing")
    public void deletePairing(HttpServletRequest request,HttpServletResponse response,Integer id){

        if (id==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            return ;
        }

        boolean deletePairingjoin=pairingService.deletePairing(id);
        if (deletePairingjoin){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除成功"));
            super.safeJsonPrint(response, result);
            return ;
        }
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeJsonPrint(response, result);
        return ;
    }

    /**
     * 修改
     */
    @RequestMapping("/updatePairing")
    public void updatePairing(HttpServletRequest request,HttpServletResponse response,String key,String value,String describe,Integer id){
        AdminBO loginUser=super.getLoginUser(request);
        Integer updateUserId = loginUser.getId();
        if ( updateUserId==null || value.equals("")){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            return ;
        }

        //根据id和键查询修改本身数据是否相同，如果相同的话进行添加，如果不相同进行查询数据库是否跟其他列相同
        int findPairingIdjoin=pairingService.findPairingId(id,key);
        if (findPairingIdjoin==0){
            int findPairingKeyjoin=pairingService.findPairingKey(key);
            if (findPairingKeyjoin==0){
                //修改配对
                boolean updatePairingjoin=pairingService.updatePairing(key,value,describe,id,updateUserId);
                if (updatePairingjoin){
                    String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功"));
                    super.safeJsonPrint(response, result);
                    return;
                }
            }
        }

        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
        super.safeJsonPrint(response, result);
        return ;

    }
}
