package com.martian.dto.teacher;

import com.martian.common.page.BasePageDto;

/**
 * 成绩统计查询DTO
 */
public class ScoreTjSearchDto extends BasePageDto {
    private static final long serialVersionUID = -7406178024782691090L;
    /**
     * 教师ID
     */
    private String teacherId;

    /**
     * 班级ID
     */
    private String clazzId;
    /**
     * 课程Id
     */
    private String courseId;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

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
}
