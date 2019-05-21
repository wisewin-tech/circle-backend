package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.RoleBO;
import com.wisewin.backend.entity.bo.RoleMenuBO;

import java.util.List;

/**
 * 王洋
 */
public interface RoleMenuDAO {
    /**
     * 增加
     */
    Integer addRoleMenu(RoleMenuBO roleMenuBO);

    /**
     *删除
     */
    Integer delRoleMenu(Integer id);

    /**
     *修改
     */
    Integer updRoleMenu(RoleMenuBO roleMenuBO);

    /**
     *查询
     */
    List<RoleBO> getRoleMenu(Integer id);
}
