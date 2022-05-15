package com.martian.entity.teacher;

import com.baomidou.mybatisplus.annotations.TableName;
import com.martian.entity.BaseEntity;
import com.martian.enums.SexEnum;

/**
 * 教师表
 */
@TableName("t_teacher")
public class Teacher extends BaseEntity {

    private static final long serialVersionUID = 1L;

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


    public String getName() {
        return name;
    }

    public Teacher setName(String name) {
        this.name = name;
        return this;
    }

    public SexEnum getSex() {
        return sex;
    }

    public Teacher setSex(SexEnum sex) {
        this.sex = sex;
        return this;
    }

    public String getBirthday() {
        return birthday;
    }

    public Teacher setBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    @Override
    public String toString() {
        return "Teacher{" +
        ", name=" + name +
        ", sex=" + sex +
        ", birthday=" + birthday +
        "}";
    }
}
