package com.martian.entity.role;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.martian.entity.BaseEntity;

/**
 * 角色-资源表
 */
@TableName("t_role_resource")
public class RoleResource extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@TableField("role_id")
	private Integer roleId;
	@TableField("resource_id")
	private Integer resourceId;


	public Integer getRoleId() {
		return roleId;
	}

	public RoleResource setRoleId(Integer roleId) {
		this.roleId = roleId;
		return this;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public RoleResource setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
		return this;
	}

}
