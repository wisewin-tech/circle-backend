package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.RoleBO;

import java.util.List;

/**
 * 王洋
 */
public interface RoleDAO {
    /**
     * 增加
     */
    Integer addRoleBO(RoleBO roleBO);

    /**
     *删除
     */
    Integer delRoleBO(Integer id);

    /**
     *修改
     */
    Integer updRoleBO(RoleBO roleBO);

    /**
     *查询
     */
    List<RoleBO> getRoleBO(Integer id);
}
