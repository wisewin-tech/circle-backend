package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.UserBackgroundBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: Bin Wang
 * @date: Created in 14:58 2019/6/4
 */
public interface UserBackgroundDAO {

    /**
     * 背景图查询
     * @param map
     * @return
     */
    List<UserBackgroundBO> queryUserBack(Map<String,Object> map);

    /**
     * 修改审核状态
     * @param id
     * @param state
     */
    void updateUserBackStatus(@Param("id") Integer id, @Param("state") String state);

    /**
     * 查询记录数
     * @param map
     * @return
     */
    Integer queryCount(Map<String,Object> map);

    /**
     * 删除
     * @param id
     */
    void delteUserBack(Integer id);
}
