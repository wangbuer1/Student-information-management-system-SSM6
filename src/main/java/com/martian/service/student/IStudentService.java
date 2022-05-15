package com.martian.service.student;

import com.baomidou.mybatisplus.service.IService;
import com.martian.common.page.PageVo;
import com.martian.dto.student.StudentDto;
import com.martian.dto.student.StudentSearchDto;
import com.martian.entity.student.Student;
import com.martian.vo.student.StudentVo;

/**
 * 学生表 服务类
 */
public interface IStudentService extends IService<Student> {
    /**
     * 搜索
     *
     * @param studentSearchDto
     * @return
     */
    PageVo searchStudentVoPage(StudentSearchDto studentSearchDto);

    /**
     * 添加
     *
     * @param studentDto
     */
    void add(StudentDto studentDto);

    /**
     * 修改
     *
     * @param studentDto
     */
    void update(StudentDto studentDto);

    /**
     * 删除
     *
     * @param studentId
     */
    void del(String studentId);

    /**
     * 获取教师信息
     *
     * @param studentId
     * @return
     */
    StudentVo getStudentVo(String studentId);

    /**
     * 修改个人信息
     *
     * @param studentDto
     */
    void updatePersonInfo(StudentDto studentDto);
}
