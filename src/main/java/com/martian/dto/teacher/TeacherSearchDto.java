package com.martian.dto.teacher;

import com.martian.common.page.BasePageDto;

/**
 * 教师搜索DTO
 */
public class TeacherSearchDto extends BasePageDto {
    private static final long serialVersionUID = -7406178024782691090L;
    /**
     * 姓名
     */
    private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

}
