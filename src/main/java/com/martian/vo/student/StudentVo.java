package com.martian.vo.student;

import com.alibaba.fastjson.annotation.JSONField;
import com.martian.common.enums.EnumObj;
import com.martian.enums.SexEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生VO
 */
public class StudentVo implements Serializable {
    private static final long serialVersionUID = 9071828857365569219L;
    /**
     * Id
     */
    private String id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 名称
     */
    private String name;
    /**
     * 学号
     */
    private String stuNo;
    /**
     * 院系Id
     */
    private String departId;
    /**
     * 院系名称
     */
    private String departName;
    /**
     * 班级Id
     */
    private String clazzId;
    /**
     * 班级名称
     */
    private String clazzName;

    /**
     * 性别：0女 1男
     */
    private SexEnum sex;
    /**
     * 性别
     */
    private EnumObj sexObj;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public EnumObj getSexObj() {
        if (this.getSex() != null) {
            return new EnumObj(this.getSex().getCode(), this.getSex().getLabel());
        }
        return new EnumObj();
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public String getClazzId() {
        return clazzId;
    }

    public void setClazzId(String clazzId) {
        this.clazzId = clazzId;
    }
}
