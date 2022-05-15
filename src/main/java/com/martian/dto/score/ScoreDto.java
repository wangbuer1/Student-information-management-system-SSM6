package com.martian.dto.score;

import java.io.Serializable;

/**
 * 成绩DTO
 */
public class ScoreDto implements Serializable {
    private static final long serialVersionUID = 5046620020957687340L;
    /**
     * Id
     */
    private String id;
    /**
     * 学生Id
     */
    private String studentId;
    /**
     * 课程Id
     */
    private String courseId;
    /**
     * 成绩
     */
    private float score;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
