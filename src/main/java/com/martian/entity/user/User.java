package com.martian.entity.user;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.martian.entity.BaseEntity;
import com.martian.enums.YNEnum;

/**
 * 用户表
 */
@TableName("t_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐值
     */
    private String salt;
    /**
     * 是否可用Y可用N禁用
     */
    @TableField("is_enable")
    private YNEnum isEnable;
    /**
     * 角色ID 管理员 教师 学生
     */
    @TableField("role_id")
    private String roleId;
    /**
     * 管理员为空 教师 学生对应的主键ID
     */
    @TableField("obj_id")
    private String objId;

    public User() {
    }

    public User(String userName, String password, String salt, String roleId, String objId, YNEnum isEnable) {
        this.userName = userName;
        this.password = password;
        this.salt = salt;
        this.roleId = roleId;
        this.objId = objId;
        this.isEnable = isEnable;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public User setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public YNEnum getIsEnable() {
        return isEnable;
    }

    public User setIsEnable(YNEnum isEnable) {
        this.isEnable = isEnable;
        return this;
    }

    public String getRoleId() {
        return roleId;
    }

    public User setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getObjId() {
        return objId;
    }

    public User setObjId(String objId) {
        this.objId = objId;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                ", userName=" + userName +
                ", password=" + password +
                ", salt=" + salt +
                ", isEnable=" + isEnable +
                ", roleId=" + roleId +
                ", objId=" + objId +
                "}";
    }
}
