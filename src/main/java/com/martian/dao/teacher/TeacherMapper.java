package com.martian.dao.teacher;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.martian.dto.teacher.ScoreTjSearchDto;
import com.martian.dto.teacher.TeacherSearchDto;
import com.martian.entity.course.Course;
import com.martian.entity.student.Student;
import com.martian.entity.teacher.Teacher;
import com.martian.vo.teacher.ScoreTjVo;
import com.martian.vo.teacher.TeacherVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教师表 Mapper 接口
 */
public interface TeacherMapper extends BaseMapper<Teacher> {

    /**
     * 搜索
     *
     * @param page
     * @param teacherSearchDto
     * @return
     */
    List<TeacherVo> searchTeacherVoPage(@Param("page") Page<TeacherVo> page, @Param("teacherSearchDto") TeacherSearchDto teacherSearchDto);

    /**
     * 获取教师信息
     *
     * @param teacherId
     * @return
     */
    TeacherVo getTeacherVo(@Param("teacherId") String teacherId);

    /**
     * 获取教师授课的课程
     *
     * @param teacherId
     * @param clazzId
     * @return
     */
    List<Course> getCourse(@Param("teacherId") String teacherId, @Param("clazzId") String clazzId);

    /**
     * 获取学生列表
     * @param courseId
     * @return
     */
    List<Student> getStudentByCourseId(@Param("courseId") String courseId);
    /**
     * 成績统计查询
     *
     * @param page
     * @param scoreTjSearchDto
     * @return
     */
    List<ScoreTjVo> scoreTjSearch(@Param("page") Page<ScoreTjVo> page, @Param("scoreTjSearchDto") ScoreTjSearchDto scoreTjSearchDto);
}
