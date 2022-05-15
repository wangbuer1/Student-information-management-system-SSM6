package com.martian.entity.course;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.martian.entity.BaseEntity;
import com.martian.enums.course.TermEnum;

/**
 * 课程表
 */
@TableName("t_course")
public class Course extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 课程名称
     */
    private String name;
    /**
     * 院系ID
     */
    @TableField("depart_id")
    private String departId;
    /**
     * 班级ID
     */
    @TableField("clazz_id")
    private String clazzId;
    /**
     * 教师ID
     */
    @TableField("teacher_id")
    private String teacherId;
    /**
     * 课程说明
     */
    @TableField("course_desc")
    private String courseDesc;
    /**
     * 年份
     */
    private String year;
    /**
     * 学期
     */
    private TermEnum term;


    public String getName() {
        return name;
    }

    public Course setName(String name) {
        this.name = name;
        return this;
    }

    public String getDepartId() {
        return departId;
    }

    public Course setDepartId(String departId) {
        this.departId = departId;
        return this;
    }

    public String getClazzId() {
        return clazzId;
    }

    public Course setClazzId(String clazzId) {
        this.clazzId = clazzId;
        return this;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public Course setTeacherId(String teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public Course setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
        return this;
    }

    public String getYear() {
        return year;
    }

    public Course setYear(String year) {
        this.year = year;
        return this;
    }

    public TermEnum getTerm() {
        return term;
    }

    public Course setTerm(TermEnum term) {
        this.term = term;
        return this;
    }

    @Override
    public String toString() {
        return "Course{" +
        ", name=" + name +
        ", departId=" + departId +
        ", clazzId=" + clazzId +
        ", teacherId=" + teacherId +
        ", courseDesc=" + courseDesc +
        ", year=" + year +
        ", term=" + term +
        "}";
    }
}
