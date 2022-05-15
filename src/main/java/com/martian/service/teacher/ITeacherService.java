package com.martian.service.teacher;

import com.baomidou.mybatisplus.service.IService;
import com.martian.common.page.PageVo;
import com.martian.dto.teacher.ScoreTjSearchDto;
import com.martian.dto.teacher.TeacherDto;
import com.martian.dto.teacher.TeacherSearchDto;
import com.martian.entity.course.Course;
import com.martian.entity.student.Student;
import com.martian.entity.teacher.Teacher;
import com.martian.vo.teacher.TeacherVo;

import java.util.List;

/**
 * 教师表 服务类
 */
public interface ITeacherService extends IService<Teacher> {
    /**
     * 搜索
     *
     * @param teacherSearchDto
     * @return
     */
    PageVo searchTeacherVoPage(TeacherSearchDto teacherSearchDto);

    /**
     * 添加
     *
     * @param teacherDto
     */
    void add(TeacherDto teacherDto);

    /**
     * 修改
     *
     * @param teacherDto
     */
    void update(TeacherDto teacherDto);

    /**
     * 删除
     *
     * @param teacherId
     */
    void del(String teacherId);

    /**
     * 获取教师信息
     *
     * @param teacherId
     * @return
     */
    TeacherVo getTeacherVo(String teacherId);

    /**
     * 获取教师列表
     *
     * @return
     */
    List<Teacher> getTeacherList();

    /**
     * 获取教师授课的课程
     * @param teacherId
     * @param clazzId
     * @return
     */
    List<Course> getCourse(String teacherId, String clazzId);

    /**
     * 获取学生列表
     * @param courseId
     * @return
     */
    List<Student> getStudentByCourseId(String courseId);

    /**
     * 成績统计查询
     * @param scoreTjSearchDto
     * @return
     */
    PageVo scoreTjSearch(ScoreTjSearchDto scoreTjSearchDto);
}
