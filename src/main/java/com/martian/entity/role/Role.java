package com.martian.entity.role;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.martian.entity.BaseEntity;

/**
 * 角色表
 */
@TableName("t_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("role_name")
    private String roleName;
    /**
     * 角色信息描述
     */
    @TableField("role_desc")
    private String roleDesc;

    public String getRoleName() {
        return roleName;
    }

    public Role setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public Role setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
        return this;
    }
}
