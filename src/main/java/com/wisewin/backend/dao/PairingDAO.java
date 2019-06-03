package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.PairingBO;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.INTERNAL;

import java.util.List;
import java.util.Map;

/**
 * 配对
 */
public interface PairingDAO {


    /**
     * 添加
     * @param pairingBO
     * @return
     */
    Integer addPairing(PairingBO pairingBO);

    /**
     * 查找数据库是否有数据
     */
    int findPairing(@Param("key")String key);

    /**
     * 根据条件显示信息
     */
    List<PairingBO> queryPairing(@Param("key") String key);

    /**
     * 删除
     */
    Integer deletePairing(@Param("id")Integer id);

    /**
     * 修改
     */
    Integer updatePairing(Map<String,Object> map);

    /**
     * 查找修改数据ID和key是否重复
     */
    int findPairingId(@Param("id") Integer id,@Param("key") String key);
    /**
     * 查找修改数据是否重复
     */
    int findPairingKey(@Param("key") String key);



}
