package com.martian.dto.course;

import com.martian.common.page.BasePageDto;
import com.martian.enums.course.TermEnum;

/**
 * 课程搜索DTO
 */
public class CourseSearchDto extends BasePageDto {
    private static final long serialVersionUID = -7406178024782691090L;

    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 学期
     */
    private TermEnum term;
    /**
     * 年份
     */
    private String year;

    /**
     * 院系Id
     */
    private String departId;
    /**
     * 班级Id
     */
    private String clazzId;
    /**
     * 教师Id
     */
    private String teacherId;

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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public TermEnum getTerm() {
        return term;
    }

    public void setTerm(TermEnum term) {
        this.term = term;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
