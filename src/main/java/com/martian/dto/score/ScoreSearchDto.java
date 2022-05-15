package com.martian.dto.score;

import com.martian.common.page.BasePageDto;
import com.martian.enums.course.TermEnum;

/**
 * 成绩搜索DTO
 */
public class ScoreSearchDto extends BasePageDto {
    private static final long serialVersionUID = -7406178024782691090L;

    /**
     * 课程Id
     */
    private String courseId;
    /**
     * 学生姓名
     */
    private String studentName;
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
    /**
     * 学生Id
     */
    private String studentId;

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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
