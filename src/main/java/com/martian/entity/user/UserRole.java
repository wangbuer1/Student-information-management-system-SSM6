package com.martian.entity.user;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.martian.entity.BaseEntity;

/**
 * 用户角色表
 */
@TableName("t_user_role")
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户Id
     */
	@TableField("user_id")
	private String userId;
    /**
     * 角色Id
     */
	@TableField("role_id")
	private String roleId;


	public String getUserId() {
		return userId;
	}

	public UserRole setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getRoleId() {
		return roleId;
	}

	public UserRole setRoleId(String roleId) {
		this.roleId = roleId;
		return this;
	}

}
