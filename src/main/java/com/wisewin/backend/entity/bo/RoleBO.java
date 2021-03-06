package com.wisewin.backend.entity.bo;

import java.util.Date;
import java.util.List;

public class RoleBO {
    private Integer id; // 角色

    private String roleName; //角色名称

    private Date createTime; //创建时间按

    private Date updateTime; // 修改时间

    private List<MenuBO> menuBOS;

    public List<MenuBO> getMenuBOS() {
        return menuBOS;
    }

    public void setMenuBOS(List<MenuBO> menuBOS) {
        this.menuBOS = menuBOS;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
