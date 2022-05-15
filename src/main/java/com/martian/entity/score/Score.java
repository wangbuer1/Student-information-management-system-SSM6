package com.martian.entity.score;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.martian.entity.BaseEntity;

/**
 * 成绩表
 */
@TableName("t_score")
public class Score extends BaseEntity {

    private static final long serialVersionUID = 1L;

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
     * 学生ID
     */
    @TableField("student_id")
    private String studentId;
    /**
     * 课程ID
     */
    @TableField("course_id")
    private String courseId;
    /**
     * 年份
     */
    private String year;
    /**
     * 成绩
     */
    private Float score;
    private String term;


    public String getDepartId() {
        return departId;
    }

    public Score setDepartId(String departId) {
        this.departId = departId;
        return this;
    }

    public String getClazzId() {
        return clazzId;
    }

    public Score setClazzId(String clazzId) {
        this.clazzId = clazzId;
        return this;
    }

    public String getStudentId() {
        return studentId;
    }

    public Score setStudentId(String studentId) {
        this.studentId = studentId;
        return this;
    }

    public String getCourseId() {
        return courseId;
    }

    public Score setCourseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    public String getYear() {
        return year;
    }

    public Score setYear(String year) {
        this.year = year;
        return this;
    }

    public Float getScore() {
        return score;
    }

    public Score setScore(Float score) {
        this.score = score;
        return this;
    }

    public String getTerm() {
        return term;
    }

    public Score setTerm(String term) {
        this.term = term;
        return this;
    }

    @Override
    public String toString() {
        return "Score{" +
        ", departId=" + departId +
        ", clazzId=" + clazzId +
        ", studentId=" + studentId +
        ", courseId=" + courseId +
        ", year=" + year +
        ", score=" + score +
        ", term=" + term +
        "}";
    }
}
