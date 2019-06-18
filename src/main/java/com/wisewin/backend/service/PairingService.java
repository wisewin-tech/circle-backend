package com.wisewin.backend.service;

import com.wisewin.backend.dao.PairingDAO;
import com.wisewin.backend.entity.bo.PairingBO;
import com.wisewin.backend.entity.param.PairingParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  配对
 */
@Service
@Transactional
public class PairingService {

    @Resource
    private PairingDAO pairingDAO;


    /**
     * 添加
     * @param
     * @return
     */
    public boolean addPairing(Integer createUserId,Integer updateUserId, PairingParam param){
        PairingBO pairingBO=new PairingBO(param.getKey(),param.getValue(),param.getDescribe(),createUserId,updateUserId);
        return pairingDAO.addPairing(pairingBO)>0;
    }

    /**
     * 查找数据库是否有数据
     */
    public Integer findPairing(String key){

        return  pairingDAO.findPairing(key);

    }

    /**
     * 根据条件显示信息
     */
    public List<PairingBO> queryPairing(String key){
        return pairingDAO.queryPairing(key);
    }

    /**
     * 删除
     */
    public boolean deletePairing(Integer id){
        return pairingDAO.deletePairing(id)>0;
    }

    /**
     * 修改
     */
    public boolean updatePairing(String key, String value,String describe,Integer id,Integer updateUserId){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("key",key);
        map.put("value",value);
        map.put("describe",describe);
        map.put("id",id);
        map.put("updateUserId",updateUserId);

        return  pairingDAO.updatePairing(map)>0;
    }

    /**
     * 查找修改数据是否重复
     */
    public int findPairingId(Integer id,String key){
        return pairingDAO.findPairingId(id,key);
    }

    /**
     * 查找修改数据ID和key是否重复
     */
    public int findPairingKey(String key){
        return pairingDAO.findPairingKey(key);
    }
}
