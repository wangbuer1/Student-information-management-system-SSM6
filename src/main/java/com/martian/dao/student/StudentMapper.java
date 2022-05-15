package com.martian.dao.student;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.martian.dto.student.StudentSearchDto;
import com.martian.entity.student.Student;
import com.martian.vo.student.StudentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学生表 Mapper 接口
 */
public interface StudentMapper extends BaseMapper<Student> {
    /**
     * 搜索
     *
     * @param page
     * @param studentSearchDto
     * @return
     */
    List<StudentVo> searchStudentVoPage(@Param("page") Page<StudentVo> page, @Param("studentSearchDto") StudentSearchDto studentSearchDto);

    /**
     * 获取学生信息
     *
     * @param studentId
     * @return
     */
    StudentVo getStudentVo(@Param("studentId") String studentId);
}
