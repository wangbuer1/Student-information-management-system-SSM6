package com.martian.vo.course;

import com.alibaba.fastjson.annotation.JSONField;
import com.martian.common.enums.EnumObj;
import com.martian.enums.course.TermEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程VO
 */
public class CourseVo implements Serializable {
    private static final long serialVersionUID = 1031491823435295680L;
    /**
     * Id
     */
    private String id;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 院系ID
     */
    private String departId;
    /**
     * 院系名称
     */
    private String departName;
    /**
     * 班级ID
     */
    private String clazzId;
    /**
     * 班级名称
     */
    private String clazzName;
    /**
     * 教师ID
     */
    private String teacherId;
    /**
     * 教师名称
     */
    private String teacherName;
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

    /**
     * 学期
     */
    private EnumObj termObj;
    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

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

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getClazzId() {
        return clazzId;
    }

    public void setClazzId(String clazzId) {
        this.clazzId = clazzId;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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

    public EnumObj getTermObj() {
        if (this.getTerm() != null) {
            return new EnumObj(this.getTerm().getCode(), this.getTerm().getLabel());
        }
        return new EnumObj();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
