package com.martian.dao.course;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.martian.dto.course.CourseSearchDto;
import com.martian.entity.course.Course;
import com.martian.vo.course.CourseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程表 Mapper 接口
 */
public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 搜索课程
     *
     * @param page
     * @param courseSearchDto
     * @return
     */
    List<CourseVo> searchCourseVoPage(@Param("page") Page<CourseVo> page, @Param("courseSearchDto") CourseSearchDto courseSearchDto);

    /**
     *
     * @param courseId
     * @return
     */
    CourseVo getCourseVo(@Param("courseId") String courseId);
}
