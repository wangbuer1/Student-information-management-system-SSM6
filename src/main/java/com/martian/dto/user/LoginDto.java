package com.martian.dto.user;

import java.io.Serializable;

/**
 * 登录DTO
 */
public class LoginDto implements Serializable {
    private static final long serialVersionUID = -2725700944502526079L;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
