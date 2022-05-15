package com.martian.vo.teacher;

import java.io.Serializable;

/**
 * 成绩统计查询VO
 */
public class ScoreTjVo implements Serializable {
    private static final long serialVersionUID = -6942198289098577674L;
    /**
     * 班級ID
     */
    private String clazzId;
    /**
     * 課程ID
     */
    private String courseId;
    /**
     * 班級名称
     */
    private String clazzName;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 平均分
     */
    private Float avgScore;
    /**
     * 60分以下人数
     */
    private Integer num60;
    /**
     * 60-80人数
     */
    private Integer num6080;
    /**
     * 80-100人数
     */
    private Integer num80100;

    public String getClazzId() {
        return clazzId;
    }

    public void setClazzId(String clazzId) {
        this.clazzId = clazzId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Float getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Float avgScore) {
        this.avgScore = avgScore;
    }

    public Integer getNum60() {
        return num60;
    }

    public void setNum60(Integer num60) {
        this.num60 = num60;
    }

    public Integer getNum6080() {
        return num6080;
    }

    public void setNum6080(Integer num6080) {
        this.num6080 = num6080;
    }

    public Integer getNum80100() {
        return num80100;
    }

    public void setNum80100(Integer num80100) {
        this.num80100 = num80100;
    }
}
