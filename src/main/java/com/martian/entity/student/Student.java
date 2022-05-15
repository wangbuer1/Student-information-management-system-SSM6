package com.martian.entity.student;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.martian.entity.BaseEntity;
import com.martian.enums.SexEnum;

/**
 * 学生表
 *
 */
@TableName("t_student")
public class Student extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 学号
     */
    @TableField("stu_no")
    private String stuNo;
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
    /**
     * 班级ID
     */
    @TableField("clazz_id")
    private String clazzId;
    /**
     * 院系ID
     */
    @TableField("depart_id")
    private String departId;


    public String getStuNo() {
        return stuNo;
    }

    public Student setStuNo(String stuNo) {
        this.stuNo = stuNo;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public SexEnum getSex() {
        return sex;
    }

    public Student setSex(SexEnum sex) {
        this.sex = sex;
        return this;
    }

    public String getBirthday() {
        return birthday;
    }

    public Student setBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getClazzId() {
        return clazzId;
    }

    public Student setClazzId(String clazzId) {
        this.clazzId = clazzId;
        return this;
    }

    public String getDepartId() {
        return departId;
    }

    public Student setDepartId(String departId) {
        this.departId = departId;
        return this;
    }

    @Override
    public String toString() {
        return "Student{" +
        ", stuNo=" + stuNo +
        ", name=" + name +
        ", sex=" + sex +
        ", birthday=" + birthday +
        ", clazzId=" + clazzId +
        "}";
    }
}
