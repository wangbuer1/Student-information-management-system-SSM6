package com.martian.dto.course;

import com.martian.enums.course.TermEnum;

import java.io.Serializable;

/**
 * 课程DTO
 */
public class CourseDto implements Serializable {
    private static final long serialVersionUID = 5046620020957687340L;
    /**
     * Id
     */
    private String id;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 班级ID
     */
    private String clazzId;
    /**
     * 教师ID
     */
    private String teacherId;
    /**
     * 课程说明
     */
    private String courseDesc;
    /**
     * 年份
     */
    private String year;
    /**
     * 学期
     */
    private TermEnum term;

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

    public String getClazzId() {
        return clazzId;
    }

    public void setClazzId(String clazzId) {
        this.clazzId = clazzId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public TermEnum getTerm() {
        return term;
    }

    public void setTerm(TermEnum term) {
        this.term = term;
    }
}
