package com.martian.vo.teacher;

import com.alibaba.fastjson.annotation.JSONField;
import com.martian.common.enums.EnumObj;
import com.martian.enums.SexEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * 教师VO
 */
public class TeacherVo implements Serializable {
    private static final long serialVersionUID = -1873972983413215834L;
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
}
