package com.martian.dto.teacher;

import com.martian.enums.SexEnum;

import java.io.Serializable;

/**
 * 教师DTO
 */
public class TeacherDto implements Serializable {
    private static final long serialVersionUID = 5046620020957687340L;
    /**
     * Id
     */
    private String id;

    /**
     * 登录名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 名称
     */
    private String name;
    /**
     * 性别：0女 1男
     */
    private SexEnum sex;
    /**
     * 生日
     */
    private String birthday;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

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
