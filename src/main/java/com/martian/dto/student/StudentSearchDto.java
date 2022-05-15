package com.martian.dto.student;

import com.martian.common.page.BasePageDto;

/**
 * 学生搜索DTO
 */
public class StudentSearchDto extends BasePageDto {

    private static final long serialVersionUID = 128895425377456898L;
    /**
     * 学生名称
     */
    private String studentName;
    /**
     * 学号
     */
    private String stuNo;
    /**
     * 班级Id
     */
    private String clazzId;
    /**
     * 院系Id
     */
    private String departId;
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClazzId() {
        return clazzId;
    }

    public void setClazzId(String clazzId) {
        this.clazzId = clazzId;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
