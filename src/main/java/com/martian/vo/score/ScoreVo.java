package com.martian.vo.score;

import com.alibaba.fastjson.annotation.JSONField;
import com.martian.vo.course.CourseVo;

import java.util.Date;

/**
 * 成绩VO
 */
public class ScoreVo extends CourseVo {
    private static final long serialVersionUID = -7143929207518891271L;
    /**
     * 成绩Id
     */
    private String scoreId;
    /**
     * 学生姓名
     */
    private String studentName;
    /**
     * 成绩
     */
    private float score;
    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getScoreId() {
        return scoreId;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }
}
