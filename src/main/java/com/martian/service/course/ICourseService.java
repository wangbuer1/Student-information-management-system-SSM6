package com.martian.service.course;

import com.baomidou.mybatisplus.service.IService;
import com.martian.common.page.PageVo;
import com.martian.dto.course.CourseDto;
import com.martian.dto.course.CourseSearchDto;
import com.martian.entity.course.Course;
import com.martian.vo.course.CourseVo;

import java.util.List;

/**
 * 课程表 服务类
 */
public interface ICourseService extends IService<Course> {
    /**
     * 添加
     *
     * @param courseDto
     */
    void add(CourseDto courseDto);

    /**
     * 修改
     *
     * @param courseDto
     */
    void update(CourseDto courseDto);

    /**
     * 删除
     *
     * @param courseId
     */
    void del(String courseId);

    /**
     * 搜索
     *
     * @param courseSearchDto
     * @return
     */
    PageVo searchCourseVoPage(CourseSearchDto courseSearchDto);

    /**
     * @param courseId
     * @return
     */
    CourseVo getCourseVo(String courseId);

    /**
     * 查询老师教授的课程
     *
     * @param teacherId
     * @return
     */
    List<Course> getTeacherCourseList(String teacherId);
    /**
     * 查询老师教授的课程
     *
     * @param teacherId
     * @param clazzId
     * @return
     */
    List<Course> getTeacherCourseList(String teacherId,String clazzId);
}
